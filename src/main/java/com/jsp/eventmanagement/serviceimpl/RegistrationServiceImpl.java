
package com.jsp.eventmanagement.serviceimpl;

import com.jsp.eventmanagement.entity.Event;
import com.jsp.eventmanagement.entity.Registration;
import com.jsp.eventmanagement.repository.EventRepository;
import com.jsp.eventmanagement.repository.RegistrationRepository;
import com.jsp.eventmanagement.repository.UserRepository;
import com.jsp.eventmanagement.service.RegistrationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RegistrationServiceImpl(
            RegistrationRepository registrationRepository,
            EventRepository eventRepository,
            UserRepository userRepository) {

        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    // Create a booking
    @Override
    @Transactional
    public Registration create(Long userId, Long eventId, Integer quantity) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Valid user ID is required");
        }

        if (eventId == null || eventId <= 0) {
            throw new IllegalArgumentException("Valid event ID is required");
        }

        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException(
                    "Ticket quantity must be at least 1");
        }

        if (quantity > 10) {
            throw new IllegalArgumentException(
                    "You can book a maximum of 10 tickets at a time");
        }

        // Verify that the user exists
        userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        // Find the event
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Event not found"));

        // Verify event status
        if (event.getStatus() == null ||
                !"PUBLISHED".equalsIgnoreCase(event.getStatus())) {
            throw new IllegalArgumentException(
                    "Event is not published and cannot be booked");
        }

        // Verify ticket availability
        if (event.getAvailableSeats() == null ||
                event.getAvailableSeats() < quantity) {
            throw new IllegalArgumentException(
                    "Not enough seats available");
        }

        // Verify ticket price
        if (event.getTicketPrice() == null ||
                event.getTicketPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Event ticket price is invalid");
        }

        // Calculate the total booking amount
        BigDecimal totalAmount = event.getTicketPrice()
                .multiply(BigDecimal.valueOf(quantity));

        // Create registration
        Registration registration = new Registration();
        registration.setUserId(userId);
        registration.setEventId(eventId);
        registration.setQuantity(quantity);
        registration.setTotalAmount(totalAmount);
        registration.setStatus("PENDING");

        // Reserve seats
        event.setAvailableSeats(
                event.getAvailableSeats() - quantity);

        eventRepository.save(event);

        // Save booking
        return registrationRepository.save(registration);
    }

    // Get all registrations
    @Override
    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    // Get registrations for a particular user
    @Override
    public List<Registration> getByUser(Long userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Valid user ID is required");
        }

        return registrationRepository.findByUserId(userId);
    }

    // Get one registration by ID
    @Override
    public Registration getById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Valid registration ID is required");
        }

        return registrationRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Booking not found"));
    }

    // Cancel a pending registration and restore the seats
    @Override
    @Transactional
    public void cancel(Long id) {

        Registration registration = getById(id);

        if (!"PENDING".equalsIgnoreCase(registration.getStatus())) {
            throw new IllegalArgumentException(
                    "Only pending bookings can be cancelled");
        }

        Event event = eventRepository.findById(
                registration.getEventId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Event not found"));

        event.setAvailableSeats(
                event.getAvailableSeats() + registration.getQuantity());

        registration.setStatus("CANCELLED");

        eventRepository.save(event);
        registrationRepository.save(registration);
    }
}
