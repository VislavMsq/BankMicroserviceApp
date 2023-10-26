package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.ProductDto;
import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Product;
import bankmicroservicesapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "transaction.debitAccountId.id", target = "debitAccountId")
    @Mapping(source = "transaction.creditAccountId.id", target = "creditAccountId")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "id", target = "id", qualifiedByName = "UUIDToString")
    @Mapping(source = "amount", target = "amount", qualifiedByName = "toDouble")
    List<TransactionDto> transactionToTransactionDto(List<Transaction> transactionsList);


    @Named("UUIDToString")
    default String UUIDToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("toDouble")
    default Double toDoubleParse(String string) {
        return Double.parseDouble(string);
    }
}
