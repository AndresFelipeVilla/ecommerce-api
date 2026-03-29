package com.felipe.spring_boot_template.users.internal.web;

import com.felipe.spring_boot_template.shared.BaseResponse;
import com.felipe.spring_boot_template.users.UserResponse;
import com.felipe.spring_boot_template.users.UsersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
class UserController {

    private final UsersApi usersApi;

    @GetMapping("/by-email")
    ResponseEntity<BaseResponse<UserResponse>> getByEmail(@RequestParam String email) {
        UserResponse user = usersApi.findByEmail(email);
        return ResponseEntity.ok(BaseResponse.ok(user));
    }
}
