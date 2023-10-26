package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Transaction;
import bankmicroservicesapp.mapper.TransactionMapper;
import bankmicroservicesapp.repository.TransactionRepository;
import bankmicroservicesapp.service.TransactionService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public List<TransactionDto> getAll() {
        List<Transaction> transactions = transactionRepository.findAll(Example.of(new Transaction()));
        return transactionMapper.transactionToTransactionDto(transactions);
    }
}
