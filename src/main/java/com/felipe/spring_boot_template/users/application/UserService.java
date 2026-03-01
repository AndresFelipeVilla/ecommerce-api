package com.felipe.spring_boot_template.users.application;

import com.felipe.spring_boot_template.exception.BusinessException;
import com.felipe.spring_boot_template.exception.ResourceNotFoundException;
import com.felipe.spring_boot_template.users.api.UserResponse;
import com.felipe.spring_boot_template.users.api.UsersApi;
import com.felipe.spring_boot_template.users.domain.Role;
import com.felipe.spring_boot_template.users.domain.User;
import com.felipe.spring_boot_template.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class UserService implements UsersApi {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User", email));
    }

    @Override
    public Optional<UserResponse> findByEmailOptional(String email) {
        return userRepository.findByEmail(email)
                .map(this::toResponse);
    }

    @Override
    @Transactional
    public UserResponse createUser(String name, String email, String password, String role) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email already registered: " + email);
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.valueOf(role.toUpperCase()))
                .build();
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .password(user.getPassword())
                .build();
    }
}
