package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.entity.User;
import bankmicroservicesapp.entity.enums.StatusAccount;
import bankmicroservicesapp.entity.enums.TypeAccount;
import bankmicroservicesapp.exeption.CreateAccountControllerException;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidStatusException;
import bankmicroservicesapp.mapper.AccountMapper;
import bankmicroservicesapp.repository.AccountRepository;
import bankmicroservicesapp.repository.UserRepository;
import bankmicroservicesapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public Account createdAccount(AccountDto accountDto) {

        Account account = accountMapper.toEntity(accountDto);
        User user = userRepository.findById(UUID.fromString(accountDto.getUserId())).orElseThrow(
                () -> new CreateAccountControllerException((ErrorMessage.CREATED_ACCOUNT_IMPOSSIBLE)));

        account.setUpdatedAt(LocalDateTime.now());
        account.setCreatedAt(LocalDateTime.now());
        account.setUser(user);
        accountRepository.save(account);
        return account;
    }


    @Override
    @Transactional
    public List<AccountDto> getAllByStatus(String status){
        try {
            StatusAccount.valueOf(status);
        } catch (Exception e) {
            throw new InvalidStatusException(ErrorMessage.INVALID_STATUS);
        }
        List<Account> accounts = accountRepository.findAllByStatus(StatusAccount.valueOf(status));
        return accountMapper.accountsToAccountsDto(accounts);
    }
}

