package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.CreateTransactionDto;
import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.service.TransactionService;
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
    public TransactionDto createTransaction(@RequestBody CreateTransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto);
    }

}
