package com.example.dashboard2026be.controller;

import com.example.dashboard2026be.dto.user.UpdateUserRequest;
import com.example.dashboard2026be.model.User;
import com.example.dashboard2026be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PutMapping(value = "/user")
    public ResponseEntity<User> updateProfile(@RequestBody UpdateUserRequest request, Authentication authentication) {

        userService.updateCurrentUser(request, authentication);
        return ResponseEntity.ok().build();
    }
}
