package bankmicroservicesapp.service;

import bankmicroservicesapp.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAll();
}
