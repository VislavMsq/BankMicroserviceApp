package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.entity.Transaction;
import bankmicroservicesapp.repository.TransactionRepository;
import bankmicroservicesapp.service.TransactionService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll(Example.of(new Transaction()));
    }
}
