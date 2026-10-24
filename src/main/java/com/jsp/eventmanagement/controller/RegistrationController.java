
package com.jsp.eventmanagement.controller;

import com.jsp.eventmanagement.dto.BookingRequest;
import com.jsp.eventmanagement.entity.Registration;
import com.jsp.eventmanagement.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Registration create(
            @Valid @RequestBody BookingRequest request) {

        return service.create(
                request.userId(),
                request.eventId(),
                request.quantity());
    }

    @GetMapping
    public List<Registration> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Registration getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Registration> getByUser(
            @PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id) {
        service.cancel(id);
    }
}
