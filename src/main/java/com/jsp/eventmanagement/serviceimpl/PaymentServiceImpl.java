package com.jsp.eventmanagement.serviceimpl;

import com.jsp.eventmanagement.entity.Event;
import com.jsp.eventmanagement.entity.Payment;
import com.jsp.eventmanagement.entity.Registration;

import com.jsp.eventmanagement.repository.EventRepository;
import com.jsp.eventmanagement.repository.PaymentRepository;
import com.jsp.eventmanagement.repository.RegistrationRepository;

import com.jsp.eventmanagement.service.PaymentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            RegistrationRepository registrationRepository,
            EventRepository eventRepository) {

        this.paymentRepository = paymentRepository;
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Payment pay(Long registrationId, boolean simulateSuccess) {

        if (registrationId == null) {
            throw new IllegalArgumentException(
                    "Registration ID is required");
        }

        Registration registration = registrationRepository
                .findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Booking not found"));

        if (!"PENDING".equalsIgnoreCase(registration.getStatus())) {
            throw new IllegalArgumentException(
                    "Booking is not awaiting payment");
        }

        if (registration.getTotalAmount() == null) {
            throw new IllegalArgumentException(
                    "Booking amount is missing");
        }

        Payment payment = paymentRepository
                .findByRegistrationId(registrationId)
                .orElseGet(Payment::new);

        payment.setRegistrationId(registrationId);
        payment.setAmount(registration.getTotalAmount());
        payment.setPaymentMethod("DEMO");
        payment.setPaymentDate(LocalDateTime.now());

        if (simulateSuccess) {

            payment.setStatus("SUCCESS");
            payment.setTransactionReference(
                    "DEMO-" + UUID.randomUUID());

            registration.setStatus("CONFIRMED");

        } else {

            payment.setStatus("FAILED");
            payment.setTransactionReference(
                    "DEMO-FAILED-" + UUID.randomUUID());

            Event event = eventRepository
                    .findById(registration.getEventId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Event not found"));

            event.setAvailableSeats(
                    event.getAvailableSeats()
                            + registration.getQuantity());

            eventRepository.save(event);

            registration.setStatus("CANCELLED");
        }

        registrationRepository.save(registration);

        return paymentRepository.save(payment);
    }
}