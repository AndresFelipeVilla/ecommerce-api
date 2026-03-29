package com.felipe.spring_boot_template.auth;

import java.util.List;

public record TokenValidationResult(
        String username,
        List<String> roles) {
}
