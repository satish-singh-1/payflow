package com.payflow.auth.service;

import com.payflow.auth.dto.LoginRequest;
import com.payflow.auth.dto.LoginResponse;
import com.payflow.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email().trim().toLowerCase(),
                                request.password()
                        )
                );

        String token = jwtService.generateToken(authentication.getName());

        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpirationMs()
        );
    }
}