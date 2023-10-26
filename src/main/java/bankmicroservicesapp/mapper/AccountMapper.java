package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "user.id", target = "userId", qualifiedByName = "UUIDToString")
    AccountDto toDto(Account account);

    List<AccountDto> accountsToAccountsDto(List<Account> accounts);

    @Mapping(source = "balance", target = "balance", qualifiedByName = "toDouble")
    @Mapping(source = "bankRating", target = "bankRating", qualifiedByName = "toInt")
    Account toEntity(AccountDto accountDto);

    @Named("toDouble")
    default Double toDoubleParse(String string) {
        return Double.parseDouble(string);
    }

    @Named("toInt")
    default Integer toIntParse(String string) {
        return Integer.parseInt(string);
    }

    @Named("UUIDToString")
    default String UUIDToString(UUID uuid) {
        return uuid.toString();
    }
}
