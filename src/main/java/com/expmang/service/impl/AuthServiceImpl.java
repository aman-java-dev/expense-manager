package com.expmang.service.impl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expmang.config.JwtUtil;
import com.expmang.dto.RegisterDTO;
import com.expmang.entity.User;
import com.expmang.exception.ResourceNotFoundException;
import com.expmang.repository.UserRepository;
import com.expmang.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
        		.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Invalid password");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
    
    public void register(RegisterDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        // 🔥 HASH PASSWORD
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }
}