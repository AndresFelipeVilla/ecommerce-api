package com.felipe.spring_boot_template.users.web;

import com.felipe.spring_boot_template.shared.api.BaseResponse;
import com.felipe.spring_boot_template.users.api.UserResponse;
import com.felipe.spring_boot_template.users.api.UsersApi;
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
