package com.bankmicroservicesapp.service.impl;

import com.bankmicroservicesapp.dto.CreateTransactionDto;
import com.bankmicroservicesapp.dto.TransactionDto;
import com.bankmicroservicesapp.entity.Account;
import com.bankmicroservicesapp.entity.Transaction;
import com.bankmicroservicesapp.exeption.DataNotExistException;
import com.bankmicroservicesapp.exeption.ErrorMessage;
import com.bankmicroservicesapp.exeption.NotEnoughMoneyException;
import com.bankmicroservicesapp.mapper.TransactionMapper;
import com.bankmicroservicesapp.repository.AccountRepository;
import com.bankmicroservicesapp.repository.TransactionRepository;
import com.bankmicroservicesapp.service.AccountService;
import com.bankmicroservicesapp.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountService accountService, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<TransactionDto> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactionMapper.transactionToTransactionDto(transactions);
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(CreateTransactionDto createTransactionDto) {
        Transaction transaction = transactionMapper.toEntity(createTransactionDto);
        transaction.setCreditAccount(accountRepository.findById(UUID.fromString(createTransactionDto.getToAccountId()))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST + " : "
                        + createTransactionDto.getToAccountId())));
        transaction.setDebitAccount(accountRepository.findById(UUID.fromString(createTransactionDto.getFromAccountId()))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST + " : "
                        + createTransactionDto.getFromAccountId())));

        Account debitAccount = updateDebitAccount(createTransactionDto, transaction);
        Account creditAccount = updateCreditAccount(createTransactionDto, transaction);
        transaction.setDebitAccount(debitAccount);
        transaction.setCreditAccount(creditAccount);
        transaction.setUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    private Account updateCreditAccount(CreateTransactionDto createTransactionDto, Transaction transaction) {
        String creditAccountId = createTransactionDto.getToAccountId();
        Account creditAccount = accountRepository.findById(UUID.fromString(creditAccountId))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.ACCOUNT_NOT_FOUND));
        BigDecimal creditBalance = creditAccount.getBalance().add(transaction.getAmount());
        creditAccount.setBalance(creditBalance);
        creditAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(creditAccount);
        return creditAccount;
    }

    private Account updateDebitAccount(CreateTransactionDto createTransactionDto, Transaction transaction) {
        String debitAccountId = createTransactionDto.getFromAccountId();
        Account debitAccount = accountRepository.findById(UUID.fromString(debitAccountId))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.ACCOUNT_IS_NULL));
        if (debitAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new NotEnoughMoneyException(ErrorMessage.NOT_ENOUGH_MONEY);
        }
        BigDecimal debitBalance = debitAccount.getBalance().subtract(transaction.getAmount());
        debitAccount.setBalance(debitBalance);
        debitAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(debitAccount);
        return debitAccount;
    }
}
