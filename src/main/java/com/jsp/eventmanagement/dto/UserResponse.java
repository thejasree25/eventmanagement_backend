
package com.jsp.eventmanagement.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        String role
) {
}
