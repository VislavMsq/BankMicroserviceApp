package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.CreateTransactionDto;
import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.entity.Transaction;
import bankmicroservicesapp.exeption.DataNotExistException;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.NotEnoughMoneyException;
import bankmicroservicesapp.mapper.TransactionMapper;
import bankmicroservicesapp.repository.AccountRepository;
import bankmicroservicesapp.repository.TransactionRepository;
import bankmicroservicesapp.service.AccountService;
import bankmicroservicesapp.service.TransactionService;
import org.springframework.stereotype.Service;

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
    public TransactionDto createTransaction(CreateTransactionDto createTransactionDto) {
        Transaction transaction = transactionMapper.toEntity(createTransactionDto);
        transaction.setCreditAccount(accountRepository.findById(UUID.fromString(createTransactionDto.getToAccountId()))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST)));
        transaction.setDebitAccount(accountRepository.findById(UUID.fromString(createTransactionDto.getFromAccountId()))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST)));

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
