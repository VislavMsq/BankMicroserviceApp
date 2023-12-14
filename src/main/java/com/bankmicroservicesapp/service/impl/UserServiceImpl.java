package com.bankmicroservicesapp.service.impl;

import com.bankmicroservicesapp.dto.UserDto;
import com.bankmicroservicesapp.entity.User;
import com.bankmicroservicesapp.exception.DataNotExistException;
import com.bankmicroservicesapp.exception.ErrorMessage;
import com.bankmicroservicesapp.mapper.UserMapper;
import com.bankmicroservicesapp.repository.UserRepository;
import com.bankmicroservicesapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() ->
                new DataNotExistException(ErrorMessage.DATA_NOT_EXIST));

        return userMapper.toDto(user);
    }
}