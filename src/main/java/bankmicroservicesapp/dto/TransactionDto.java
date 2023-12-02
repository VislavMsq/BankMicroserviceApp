package bankmicroservicesapp.dto;

import lombok.Data;

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
