package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.exeption.DataNotExistException;
import bankmicroservicesapp.exeption.ErrorMessage;
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
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        return userMapper.toDto(user.orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST)));
    }
}