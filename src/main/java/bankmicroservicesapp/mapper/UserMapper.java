package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.dto.UserDto;
import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.entity.User;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "UUIDToString")
    @Mapping(source = "account.id",target = "accountId", qualifiedByName = "UUIDToString")
    @Mapping(source = "employee.id",target = "employeeId", qualifiedByName = "UUIDToString")
    UserDto toDto(User User);

    @Named("UUIDToString")
    default String UUIDToString(UUID uuid) {
        return uuid.toString();
    }
}
