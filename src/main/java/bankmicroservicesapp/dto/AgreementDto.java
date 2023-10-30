package bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class AgreementDto {
    // product
    String productName;
    //user
    String userId;
    // current
    String interestRate;
    String currencyCode;
    String status;
    String discount;
    String agreementLimit;
    String sum;



}
