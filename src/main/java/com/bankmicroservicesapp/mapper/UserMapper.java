package com.bankmicroservicesapp.mapper;

import com.bankmicroservicesapp.dto.UserDto;
import com.bankmicroservicesapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

}
