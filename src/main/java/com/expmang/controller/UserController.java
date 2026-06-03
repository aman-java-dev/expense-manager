package com.expmang.controller;

import com.expmang.dto.UserDTO;
import com.expmang.entity.User;
import com.expmang.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User Profile APIs")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateProfile(dto));
    }
}