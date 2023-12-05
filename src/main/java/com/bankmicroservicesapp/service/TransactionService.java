package com.bankmicroservicesapp.service;

import com.bankmicroservicesapp.dto.CreateTransactionDto;
import com.bankmicroservicesapp.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAll();

    TransactionDto createTransaction(CreateTransactionDto createTransactionDto);
}


