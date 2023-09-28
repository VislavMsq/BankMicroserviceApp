package bankmicroservicesapp.dto;

import bankmicroservicesapp.entity.enums.StatusAgreement;
import lombok.Data;

@Data
public class AgreementDto {
    // current
    String interestRate;
    String currencyCode;
    String status;
    String discount;
    String agreementLimit;
    String sum;

    // employee
    String managerId;


}
