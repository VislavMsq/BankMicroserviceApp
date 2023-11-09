package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "transaction.debitAccountId.id", target = "debitAccountId")
    @Mapping(source = "transaction.creditAccountId.id", target = "creditAccountId")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "type", target = "type")
    Transaction toEntity(TransactionDto transactionDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount", qualifiedByName = "stringToBigDecimal")
    List<TransactionDto> transactionToTransactionDto(List<Transaction> transactionsList);

    @Named("stringToBigDecimal")
    default BigDecimal stringToBigDecimal(String s) {
        return new BigDecimal(s);
    }
}
