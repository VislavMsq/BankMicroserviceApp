package com.bankmicroservicesapp.dto;

import lombok.Data;

@Data
public class UserDto {
    String id;
    String taxCode;
    String firstName;
    String lastName;
    String email;
    String userPassword;
    String address;
    String phone;
//    // account
//    String accountId;
//    // employee
//    String employeeId;
}
