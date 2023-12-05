package com.bankmicroservicesapp.service;

import com.bankmicroservicesapp.dto.AccountDto;
import com.bankmicroservicesapp.exeption.InvalidStatusException;

import java.util.List;

public interface AccountService {
    AccountDto createdAccount(AccountDto accountDto);

    List<AccountDto> getAllByStatus(String status) throws InvalidStatusException;

    AccountDto getById(String id);
}
