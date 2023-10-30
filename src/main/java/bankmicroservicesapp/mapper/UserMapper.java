package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "account.id",target = "accountId")
    @Mapping(source = "employee.id",target = "employeeId")
    UserDto toDto(User User);

}
