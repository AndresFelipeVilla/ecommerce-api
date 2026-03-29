package com.felipe.spring_boot_template.users.internal.service;

import com.felipe.spring_boot_template.exception.BusinessException;
import com.felipe.spring_boot_template.exception.ResourceNotFoundException;
import com.felipe.spring_boot_template.users.CreateUserCommand;
import com.felipe.spring_boot_template.users.UserResponse;
import com.felipe.spring_boot_template.users.UsersApi;
import com.felipe.spring_boot_template.users.internal.domain.Role;
import com.felipe.spring_boot_template.users.internal.domain.User;
import com.felipe.spring_boot_template.users.internal.domain.UserRepository;
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
    public UserResponse createUser(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new BusinessException("Email already registered: " + command.email());
        }
        User user = User.builder()
                .name(command.name())
                .email(command.email())
                .password(passwordEncoder.encode(command.password()))
                .role(Role.valueOf(command.role().toUpperCase()))
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
