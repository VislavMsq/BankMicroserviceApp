package com.bankmicroservicesapp.service.impl;

import com.bankmicroservicesapp.dto.AccountDto;
import com.bankmicroservicesapp.entity.Account;
import com.bankmicroservicesapp.entity.User;
import com.bankmicroservicesapp.entity.enums.StatusAccount;
import com.bankmicroservicesapp.exception.CreateAccountControllerException;
import com.bankmicroservicesapp.exception.DataNotExistException;
import com.bankmicroservicesapp.exception.ErrorMessage;
import com.bankmicroservicesapp.exception.InvalidStatusException;
import com.bankmicroservicesapp.mapper.AccountMapper;
import com.bankmicroservicesapp.repository.AccountRepository;
import com.bankmicroservicesapp.repository.UserRepository;
import com.bankmicroservicesapp.service.AccountService;
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
    public AccountDto createdAccount(AccountDto accountDto) {

        Account account = accountMapper.toEntity(accountDto);
        User user = userRepository.findById(UUID.fromString(accountDto.getUserId())).orElseThrow(
                () -> new CreateAccountControllerException((ErrorMessage.CREATED_ACCOUNT_IMPOSSIBLE)));

        account.setUpdatedAt(LocalDateTime.now());
        account.setCreatedAt(LocalDateTime.now());
        account.setUser(user);
        accountRepository.save(account);
        return accountMapper.toDto(account);
    }


    @Override
    @Transactional
    public List<AccountDto> getAllByStatus(String status) {
        try {
            StatusAccount.valueOf(status);
        } catch (Exception e) {
            throw new InvalidStatusException(ErrorMessage.INVALID_STATUS);
        }
        List<Account> accounts = accountRepository.findAllByStatus(StatusAccount.valueOf(status));
        return accountMapper.accountsToAccountsDto(accounts);
    }

    @Override
    public AccountDto getById(String id) {
        return accountMapper.toDto(accountRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST)));
    }
}

