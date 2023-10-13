package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.exeption.CreateAccountControllerException;

import java.util.List;

public interface AccountService {
    Account createdAccount(AccountDto accountDto) throws CreateAccountControllerException;

    List<AccountDto> getAllByStatus(String status);
}
