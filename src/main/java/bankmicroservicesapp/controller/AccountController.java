package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createdAccount(accountDto);
    }

    @GetMapping("/get-all/by-status")
    public List<AccountDto> getAllStatus(@RequestParam(name = "status") String status) { // передаем параментр
        return accountService.getAllByStatus(status);
    }
}
