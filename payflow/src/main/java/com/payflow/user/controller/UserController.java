package com.payflow.user.controller;

import com.payflow.user.dto.UserResponse;
import com.payflow.user.entity.User;
import com.payflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(
            Authentication authentication) {

        User user = userRepository.findByEmail(
                authentication.getName()
        ).orElseThrow(() ->
                new org.springframework.security.core.userdetails
                        .UsernameNotFoundException("User not found")
        );

        UserResponse response = new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }
}