package com.expmang.service;

import com.expmang.dto.RegisterDTO;

public interface AuthService {
    String login(String email, String password);
    public void register(RegisterDTO dto);
}
