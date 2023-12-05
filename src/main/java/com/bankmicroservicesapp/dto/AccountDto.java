package com.bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class AccountDto {
    String userId;
    String name;
    String type;
    String status;
    String currencyCode;
    String bankRating;
    String balance;
}
