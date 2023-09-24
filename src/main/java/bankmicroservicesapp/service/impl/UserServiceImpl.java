package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.repository.UserRepository;
import bankmicroservicesapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service // указывает спрингу, что это бин и чтобы
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(String userId) {
        return userRepository.findById(UUID.fromString(userId)).orElse(null); // закастил ююд до стрига
    }
}
