package bankmicroservicesapp.controller;

import bankmicroservicesapp.controller.util.Valid;
import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.exeption.UserNotExistException;
import bankmicroservicesapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable("userId") String userId) {
        Optional<User> optUser;
        if (!Valid.isValidUUID(userId)) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
        optUser = userService.findById(userId);

        if (optUser.isEmpty()) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        return optUser.get();
    }
}

