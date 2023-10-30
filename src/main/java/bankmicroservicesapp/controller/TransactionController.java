package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
