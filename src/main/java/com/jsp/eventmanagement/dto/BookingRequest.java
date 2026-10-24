package com.jsp.eventmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookingRequest(
    @NotNull @Positive Long userId,
    @NotNull @Positive Long eventId,
    @NotNull @Positive Integer quantity
) {
}