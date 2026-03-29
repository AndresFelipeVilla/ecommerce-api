package com.felipe.spring_boot_template.auth.internal.web;

import com.felipe.spring_boot_template.auth.AuthApi;
import com.felipe.spring_boot_template.auth.internal.dto.AuthResponse;
import com.felipe.spring_boot_template.auth.internal.dto.LoginRequest;
import com.felipe.spring_boot_template.auth.internal.dto.RegisterRequest;
import com.felipe.spring_boot_template.shared.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController {

    private final AuthApi authApi;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    ResponseEntity<BaseResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        // Credential validation is an HTTP/security-layer responsibility
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = authApi.authenticate(request.getEmail(), request.getPassword());
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .email(request.getEmail())
                .build();
        return ResponseEntity.ok(BaseResponse.ok(authResponse, "Login successful"));
    }

    @PostMapping("/register")
    ResponseEntity<BaseResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        String token = authApi.register(request.getName(), request.getEmail(), request.getPassword());
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .email(request.getEmail())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.ok(authResponse, "Registration successful"));
    }
}

