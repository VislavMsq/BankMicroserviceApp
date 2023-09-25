package bankmicroservicesapp.service;

import bankmicroservicesapp.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(String userId);
}
