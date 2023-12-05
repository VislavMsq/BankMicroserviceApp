package com.bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    String id;
    String productType;
    String productStatus;
    String interestRate;
}
