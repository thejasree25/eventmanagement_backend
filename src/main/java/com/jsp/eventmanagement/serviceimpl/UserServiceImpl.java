
package com.jsp.eventmanagement.serviceimpl;

import java.util.Locale;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.eventmanagement.entity.User;
import com.jsp.eventmanagement.repository.UserRepository;
import com.jsp.eventmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User register(User user) {
        String email = user.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email is already registered"
            );
        }

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User login(String email, String password) {
        User user = userRepository
                .findByEmail(email.trim().toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid email or password"
                ));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }

        return user;
    }

    @Override
    @Transactional
    public User googleLogin(String email, String name) {
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);

        return userRepository.findByEmail(normalizedEmail)
                .orElseGet(() -> {
                    User user = new User();

                    user.setName(
                            name == null || name.isBlank()
                                    ? normalizedEmail
                                    : name
                    );

                    user.setEmail(normalizedEmail);

                    // Google-created accounts do not use password login
                    // with this randomly generated password.
                    user.setPassword(passwordEncoder.encode(
                            UUID.randomUUID().toString()
                                    + UUID.randomUUID()
                    ));

                    user.setRole("USER");

                    return userRepository.save(user);
                });
    }
}
