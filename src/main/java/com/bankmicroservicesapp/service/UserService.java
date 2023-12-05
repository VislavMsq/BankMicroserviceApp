package com.bankmicroservicesapp.service;

import com.bankmicroservicesapp.dto.UserDto;

public interface UserService {
    UserDto findById(String userId);
}
