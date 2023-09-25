package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;

public interface AccountService {
    Account createdAccount(AccountDto accountDto);
}
