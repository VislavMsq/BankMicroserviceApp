package bankmicroservicesapp.dto;

import bankmicroservicesapp.entity.enums.StatusAgreement;
import lombok.Data;

@Data
public class AgreementDto {
    // product
    String productName;
    // current
    String interestRate;
    String currencyCode;
    String status;
    String discount;
    String agreementLimit;
    String sum;



}
