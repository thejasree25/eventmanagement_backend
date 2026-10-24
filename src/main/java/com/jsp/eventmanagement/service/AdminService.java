
package com.jsp.eventmanagement.service;

import java.util.List;

import com.jsp.eventmanagement.dto.AdminDashboardResponse;
import com.jsp.eventmanagement.entity.User;
import com.jsp.eventmanagement.entity.Event;
import com.jsp.eventmanagement.entity.Venue;
import com.jsp.eventmanagement.entity.Category;
import com.jsp.eventmanagement.entity.Registration;
import com.jsp.eventmanagement.entity.Payment;

public interface AdminService {

    AdminDashboardResponse getDashboard();

    List<User> getAllUsers();

    List<Event> getAllEvents();

    List<Venue> getAllVenues();

    List<Category> getAllCategories();

    List<Registration> getAllRegistrations();

    List<Payment> getAllPayments();
}
