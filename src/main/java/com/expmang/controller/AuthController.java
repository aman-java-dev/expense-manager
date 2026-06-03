package com.expmang.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expmang.dto.AuthRequestDTO;
import com.expmang.dto.AuthResponseDTO;
import com.expmang.dto.RegisterDTO;
import com.expmang.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Authentication", description = "Register aur Login APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {

        String token = authService.login(request.getEmail(), request.getPassword());

        return new AuthResponseDTO(token);
    }
    
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterDTO dto) {
        authService.register(dto);
        return "User registered successfully";
    }
}