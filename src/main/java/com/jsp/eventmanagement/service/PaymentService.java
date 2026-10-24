package com.jsp.eventmanagement.service;

import com.jsp.eventmanagement.entity.Payment;

public interface PaymentService {

    Payment pay(Long registrationId, boolean simulateSuccess);
}