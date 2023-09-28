package bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    String productId;
    String productType;
    String productStatus;
    String interestRate;
}
