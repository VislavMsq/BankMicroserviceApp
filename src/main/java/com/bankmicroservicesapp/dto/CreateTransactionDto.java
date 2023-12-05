package com.bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class CreateTransactionDto {
    String fromAccountId;
    String toAccountId;
    // transaction
    String type;
    String amount;
    String description;

}
