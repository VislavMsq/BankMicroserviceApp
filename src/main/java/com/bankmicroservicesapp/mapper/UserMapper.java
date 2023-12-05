package com.bankmicroservicesapp.mapper;

import com.bankmicroservicesapp.dto.UserDto;
import com.bankmicroservicesapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "account.id",target = "accountId")
//    @Mapping(source = "employee.id",target = "employeeId")
    UserDto toDto(User user);

}
