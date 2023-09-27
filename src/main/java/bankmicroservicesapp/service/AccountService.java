package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;

import java.util.List;

public interface AccountService {
    Account createdAccount(AccountDto accountDto);

    List<AccountDto> getAllAccountWhereStatusIsNew();
}
