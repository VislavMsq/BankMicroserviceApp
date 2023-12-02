package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.exeption.InvalidStatusException;

import java.util.List;

public interface AccountService {
    Account createdAccount(AccountDto accountDto);

    List<AccountDto> getAllByStatus(String status) throws InvalidStatusException;

    AccountDto getById(String id);
}
