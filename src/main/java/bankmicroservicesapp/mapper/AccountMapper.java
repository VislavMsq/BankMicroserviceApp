package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Named("toDto")
    @Mapping(source = "name", target = "name")
    AccountDto toDto(Account account);

    @IterableMapping(qualifiedByName = "toDto")
        // указываем, чтобы каждый акк должен прогонятся через метод и записываться в лист
    List<AccountDto> accountToAccountsDto(List<Account> accounts);

}
