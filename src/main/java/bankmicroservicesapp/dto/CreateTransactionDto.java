package bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class CreateTransactionDto {
    String fromAccountId;
    String toAccountId;
    // transaction
    String transaction_type;
    String amount;
    String description;

}
