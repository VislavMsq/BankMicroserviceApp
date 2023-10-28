package bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class ProductUpdateDto {
    String productType;
    String productStatus;
    String interestRate;
}
