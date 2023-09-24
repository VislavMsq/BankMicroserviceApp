package bankmicroservicesapp.controller;

import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account") // маршрут
@RequiredArgsConstructor
//прописывается если автоматически генерируешь зависимость без создания контруктора,
//                          под капотом тоже есть конструктор
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}") // указываю, что прийдет cardId и я его передам дальше
    public User getById(@PathVariable(name = "userId") String userId) {
        return userService.getById(userId);
    }
}

