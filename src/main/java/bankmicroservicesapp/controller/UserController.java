package bankmicroservicesapp.controller;

import bankmicroservicesapp.controller.util.Valid;
import bankmicroservicesapp.dto.UserDto;
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
    public UserDto getById(@PathVariable("userId") String userId) {
        UserDto optUser;
        optUser = userService.findById(userId);

        return optUser;
    }
}

