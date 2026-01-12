package com.example.dashboard2026be.service;

import com.example.dashboard2026be.dto.user.UpdateUserRequest;
import com.example.dashboard2026be.model.User;
import com.example.dashboard2026be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void updateCurrentUser(UpdateUserRequest req, Authentication auth) {
    String email = auth.getName(); // з JWT!

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    if (req.name() != null) {
      user.setName(req.name());
    }

    if (req.age() != null) {
      user.setAge(req.age());
    }

    userRepository.save(user);
  }
}
