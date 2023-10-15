package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.exeption.CreateAccountControllerException;
import bankmicroservicesapp.exeption.InvalidStatusException;

import java.util.List;

public interface AccountService {
    Account createdAccount(AccountDto accountDto) throws CreateAccountControllerException;

    List<AccountDto> getAllByStatus(String status) throws InvalidStatusException;
}
