package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAll();
}
