package com.felipe.spring_boot_template.users.infrastructure.persistence;

import com.felipe.spring_boot_template.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
