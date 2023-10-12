package bankmicroservicesapp.controller;

import bankmicroservicesapp.entity.User;
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
        try {
            optUser = userService.findById(userId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
        if (optUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optUser.get();
    }
}

