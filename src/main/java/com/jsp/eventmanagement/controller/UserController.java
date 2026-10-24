
package com.jsp.eventmanagement.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.jsp.eventmanagement.dto.GoogleLoginRequest;
import com.jsp.eventmanagement.dto.LoginRequest;
import com.jsp.eventmanagement.dto.RegisterRequest;
import com.jsp.eventmanagement.dto.UserResponse;
import com.jsp.eventmanagement.entity.User;
import com.jsp.eventmanagement.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    public UserController(
            UserService userService,
            GoogleIdTokenVerifier googleIdTokenVerifier) {
        this.userService = userService;
        this.googleIdTokenVerifier = googleIdTokenVerifier;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid @RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhone(request.phone());

        return toResponse(userService.register(user));
    }

    @PostMapping("/login")
    public UserResponse login(
            @Valid @RequestBody LoginRequest request) {

        User user = userService.login(
                request.email(),
                request.password()
        );

        return toResponse(user);
    }

    @PostMapping("/google-login")
    public UserResponse googleLogin(
            @RequestBody GoogleLoginRequest request) {

        if (request == null
                || request.credential() == null
                || request.credential().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Google credential is required"
            );
        }

        try {
            GoogleIdToken idToken =
                    googleIdTokenVerifier.verify(request.credential());

            if (idToken == null) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid or expired Google ID token"
                );
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            if (!Boolean.TRUE.equals(payload.getEmailVerified())) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Google email is not verified"
                );
            }

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            if (email == null || email.isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Google account email is missing"
                );
            }

            User user = userService.googleLogin(email, name);

            return toResponse(user);

        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (GeneralSecurityException | IOException ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Unable to verify Google token. Please try again."
            );
        }
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }
}
