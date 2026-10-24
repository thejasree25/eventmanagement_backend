
package com.jsp.eventmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.eventmanagement.dto.AdminDashboardResponse;
import com.jsp.eventmanagement.entity.User;
import com.jsp.eventmanagement.entity.Event;
import com.jsp.eventmanagement.entity.Venue;
import com.jsp.eventmanagement.entity.Category;
import com.jsp.eventmanagement.entity.Registration;
import com.jsp.eventmanagement.entity.Payment;
import com.jsp.eventmanagement.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public AdminDashboardResponse getDashboard() {
        return adminService.getDashboard();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return adminService.getAllEvents();
    }

    @GetMapping("/venues")
    public List<Venue> getAllVenues() {
        return adminService.getAllVenues();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return adminService.getAllCategories();
    }

    @GetMapping("/registrations")
    public List<Registration> getAllRegistrations() {
        return adminService.getAllRegistrations();
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return adminService.getAllPayments();
    }
}
