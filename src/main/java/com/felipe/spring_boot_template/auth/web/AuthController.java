package com.felipe.spring_boot_template.auth.web;

import com.felipe.spring_boot_template.auth.api.AuthApi;
import com.felipe.spring_boot_template.auth.domain.AuthResponse;
import com.felipe.spring_boot_template.auth.domain.LoginRequest;
import com.felipe.spring_boot_template.auth.domain.RegisterRequest;
import com.felipe.spring_boot_template.shared.api.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthController {

    private final AuthApi authApi;

    @PostMapping("/login")
    ResponseEntity<BaseResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
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
