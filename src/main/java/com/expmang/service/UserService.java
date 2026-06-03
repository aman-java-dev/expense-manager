package com.expmang.service;

import com.expmang.dto.UserDTO;
import com.expmang.entity.User;

public interface UserService {
    User getProfile();
    User updateProfile(UserDTO dto);
}
