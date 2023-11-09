package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.entity.Transaction;
import bankmicroservicesapp.exeption.DataNotExistException;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.NotEnoughMoneyException;
import bankmicroservicesapp.mapper.TransactionMapper;
import bankmicroservicesapp.repository.AccountRepository;
import bankmicroservicesapp.repository.TransactionRepository;
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

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountRepository accountRepository) {
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
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        Account debitAccount = updateDebitAccount(transactionDto, transaction);
        Account creditAccount = updateDebitAccount(transactionDto, transaction);
        transaction.setDebitAccountId(debitAccount);
        transaction.setCreditAccountId(creditAccount);
        transaction.setUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    // TODO дописать updateCreditAccount



    private Account updateDebitAccount(TransactionDto transactionDto, Transaction transaction) {
        String debitAccountId = transactionDto.getCreditAccountId();
        Account debitAccount = accountRepository.findById(UUID.fromString(debitAccountId))
                .orElseThrow(() -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST));
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
