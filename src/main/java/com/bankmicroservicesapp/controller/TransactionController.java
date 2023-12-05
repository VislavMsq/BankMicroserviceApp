package com.bankmicroservicesapp.controller;

import com.bankmicroservicesapp.dto.CreateTransactionDto;
import com.bankmicroservicesapp.dto.TransactionDto;
import com.bankmicroservicesapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public List<TransactionDto> getAllTransaction() {
        return transactionService.getAll();
    }

    @PostMapping("/create")
    public TransactionDto createTransaction(@RequestBody CreateTransactionDto createTransactionDto){
        return transactionService.createTransaction(createTransactionDto);
    }

}
