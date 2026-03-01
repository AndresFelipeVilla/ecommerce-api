package com.felipe.spring_boot_template.auth.application;

import com.felipe.spring_boot_template.auth.api.AuthApi;
import com.felipe.spring_boot_template.auth.api.TokenValidationResult;
import com.felipe.spring_boot_template.users.api.UsersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class AuthService implements AuthApi {

    private final UsersApi usersApi;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public String authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtService.generateToken(userDetails);
    }

    @Override
    public String register(String name, String email, String password) {
        usersApi.createUser(name, email, password, "USER");
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtService.generateToken(userDetails);
    }

    @Override
    public String extractUsername(String token) {
        return jwtService.extractUsername(token);
    }

    @Override
    public Optional<TokenValidationResult> validateToken(String token) {
        if (!jwtService.isTokenValid(token)) {
            return Optional.empty();
        }
        String username = jwtService.extractUsername(token);
        List<String> roles = jwtService.extractRoles(token);
        return Optional.of(new TokenValidationResult(username, roles));
    }
}
