package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.entity.User;

import java.util.Optional;

public interface UserService {
    UserDto findById(String userId);
}
