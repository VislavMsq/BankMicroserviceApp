package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.service.AccountService;
import bankmicroservicesapp.validation.annotation.ValidUUID;
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
    public List<AccountDto> getAllStatus(@RequestParam(name = "status") String status) {
        return accountService.getAllByStatus(status);
    }

    @GetMapping("/get/by-id")
    public AccountDto get(@ValidUUID @RequestParam (name = "id") String id){
        return accountService.getById(id);
    }
}
