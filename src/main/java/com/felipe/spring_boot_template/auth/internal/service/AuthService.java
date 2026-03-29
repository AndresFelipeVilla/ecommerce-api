package com.felipe.spring_boot_template.auth.internal.service;

import com.felipe.spring_boot_template.auth.AuthApi;
import com.felipe.spring_boot_template.auth.TokenValidationResult;
import com.felipe.spring_boot_template.auth.internal.security.JwtService;
import com.felipe.spring_boot_template.users.CreateUserCommand;
import com.felipe.spring_boot_template.users.UserResponse;
import com.felipe.spring_boot_template.users.UsersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class AuthService implements AuthApi {

    private final UsersApi usersApi;
    private final JwtService jwtService;

    /**
     * Generates a JWT for the given email.
     * Credential validation (password check) is the responsibility of the
     * caller (AuthController) via AuthenticationManager — this keeps
     * the domain service free of security-infrastructure dependencies.
     */
    @Override
    public String authenticate(String email, String password) {
        return jwtService.generateToken(buildUserDetails(usersApi.findByEmail(email)));
    }

    @Override
    public String register(String name, String email, String password) {
        usersApi.createUser(new CreateUserCommand(name, email, password, "USER"));
        return jwtService.generateToken(buildUserDetails(usersApi.findByEmail(email)));
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

    private UserDetails buildUserDetails(UserResponse user) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}

