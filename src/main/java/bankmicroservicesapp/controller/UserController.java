package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable("userId") String userId) {
        return userService.findById(userId);
    }
}

