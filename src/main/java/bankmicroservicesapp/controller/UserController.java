package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.service.UserService;
import bankmicroservicesapp.validation.annotation.ValidUUID;
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

    @GetMapping("/by-id/{id}")
    public UserDto getById(@ValidUUID @PathVariable("id") String id) {
        return userService.findById(id);
    }
}

