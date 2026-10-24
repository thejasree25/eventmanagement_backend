package com.jsp.eventmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(
    @NotNull @Positive Long registrationId,
    @NotNull Boolean simulateSuccess
) {
}