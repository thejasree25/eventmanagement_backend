package com.jsp.eventmanagement.service;

import com.jsp.eventmanagement.entity.Registration;
import java.util.List;

public interface RegistrationService {

    Registration create(Long userId, Long eventId, Integer quantity);

    List<Registration> getAll();

    Registration getById(Long id);

    List<Registration> getByUser(Long userId);

    void cancel(Long id);
}