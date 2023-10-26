package bankmicroservicesapp.dto;

import bankmicroservicesapp.entity.Account;
import lombok.Data;

import java.util.UUID;

@Data
public class TransactionDto {
    // current
    String id;
    String type;
    String amount;
    String description;
    // account
    String debitAccountId;
    String creditAccountId;


}
