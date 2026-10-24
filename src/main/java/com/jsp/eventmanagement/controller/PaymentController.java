package com.jsp.eventmanagement.controller;

import com.jsp.eventmanagement.dto.PaymentRequest;
import com.jsp.eventmanagement.entity.Payment;
import com.jsp.eventmanagement.service.PaymentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/demo")
    public ResponseEntity<?> demoPayment(
            @Valid @RequestBody PaymentRequest request) {
        try {
            Payment payment = paymentService.pay(
                    request.registrationId(),
                    request.simulateSuccess()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(payment);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}