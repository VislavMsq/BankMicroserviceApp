package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.entity.enums.StatusAccount;
import bankmicroservicesapp.entity.enums.TypeAccount;
import bankmicroservicesapp.repository.AccountRepository;
import bankmicroservicesapp.repository.UserRepository;
import bankmicroservicesapp.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository,UserRepository  userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Account createdAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        account.setType(TypeAccount.valueOf(accountDto.getType()));
        account.setStatus(StatusAccount.valueOf(accountDto.getStatus()));
        account.setBalance(accountDto.getBalance());
        account.setCurrencyCode(accountDto.getCurrencyCode());
        account.setBankRating(accountDto.getBankRating());
        account.setUpdatedAt(LocalDateTime.now());
        account.setCreatedAt(LocalDateTime.now());
        User user = userRepository.findById(UUID.fromString(accountDto.getUserId())).orElse(null);
        account.setUser(user);
        accountRepository.save(account);
        return account;
    }
}
