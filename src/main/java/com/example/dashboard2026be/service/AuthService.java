package com.example.dashboard2026be.service;

import com.example.dashboard2026be.dto.auth.AuthResponse;
import com.example.dashboard2026be.dto.auth.LoginRequest;
import com.example.dashboard2026be.dto.auth.RegisterRequest;
import com.example.dashboard2026be.model.User;
import com.example.dashboard2026be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.age() != null) {
            user.setAge(request.age());
        }

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
