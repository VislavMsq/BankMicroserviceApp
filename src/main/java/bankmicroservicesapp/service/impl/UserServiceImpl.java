package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.controller.util.Valid;
import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.exeption.UserNotExistException;
import bankmicroservicesapp.mapper.UserMapper;
import bankmicroservicesapp.repository.UserRepository;
import bankmicroservicesapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findById(String userId) {
        if (!Valid.isValidUUID(userId)) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        return userMapper.toDto(user.orElseThrow(() -> new UserNotExistException(ErrorMessage.USER_NOT_EXIST)));
    }
}