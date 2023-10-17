package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.repository.UserRepository;
import bankmicroservicesapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }
}