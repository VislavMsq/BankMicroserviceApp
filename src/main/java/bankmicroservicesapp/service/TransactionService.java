package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.CreateTransactionDto;
import bankmicroservicesapp.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAll();

    TransactionDto createTransaction(CreateTransactionDto createTransactionDto);
}


