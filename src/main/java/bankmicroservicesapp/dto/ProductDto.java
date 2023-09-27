package bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String productId;
    private String productType;
    private String productStatus;
    private Double interestRate;
}
