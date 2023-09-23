package bankmicroservicesapp.controller;

import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService; // интерфейс

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/{cardId}") // указываю, что прийдет cardId и я его передам дальше
    public Account getById(@PathVariable(name = "cardId") String cardId){
        return accountService.getById(cardId);
    }
}
