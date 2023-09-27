package bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String userId;
    private String name;
    private String type;
    private String status;
    private Double balance;
    private String currencyCode;
    private Integer bankRating;
}
