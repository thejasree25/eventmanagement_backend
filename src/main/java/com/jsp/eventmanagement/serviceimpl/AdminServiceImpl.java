
package com.jsp.eventmanagement.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.eventmanagement.dto.AdminDashboardResponse;
import com.jsp.eventmanagement.entity.User;
import com.jsp.eventmanagement.entity.Event;
import com.jsp.eventmanagement.entity.Venue;
import com.jsp.eventmanagement.entity.Category;
import com.jsp.eventmanagement.entity.Registration;
import com.jsp.eventmanagement.entity.Payment;
import com.jsp.eventmanagement.repository.UserRepository;
import com.jsp.eventmanagement.repository.EventRepository;
import com.jsp.eventmanagement.repository.VenueRepository;
import com.jsp.eventmanagement.repository.CategoryRepository;
import com.jsp.eventmanagement.repository.RegistrationRepository;
import com.jsp.eventmanagement.repository.PaymentRepository;
import com.jsp.eventmanagement.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final CategoryRepository categoryRepository;
    private final RegistrationRepository registrationRepository;
    private final PaymentRepository paymentRepository;

    public AdminServiceImpl(
            UserRepository userRepository,
            EventRepository eventRepository,
            VenueRepository venueRepository,
            CategoryRepository categoryRepository,
            RegistrationRepository registrationRepository,
            PaymentRepository paymentRepository) {

        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.categoryRepository = categoryRepository;
        this.registrationRepository = registrationRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public AdminDashboardResponse getDashboard() {
        return new AdminDashboardResponse(
                userRepository.count(),
                eventRepository.count(),
                venueRepository.count(),
                categoryRepository.count(),
                registrationRepository.count(),
                paymentRepository.count()
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
