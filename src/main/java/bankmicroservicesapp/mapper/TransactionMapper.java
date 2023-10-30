package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "transaction.debitAccountId.id", target = "debitAccountId")
    @Mapping(source = "transaction.creditAccountId.id", target = "creditAccountId")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    List<TransactionDto> transactionToTransactionDto(List<Transaction> transactionsList);

}
