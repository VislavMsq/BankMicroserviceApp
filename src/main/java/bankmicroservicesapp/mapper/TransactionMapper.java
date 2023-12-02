package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.CreateTransactionDto;
import bankmicroservicesapp.dto.TransactionDto;
import bankmicroservicesapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "transaction.debitAccount.id", target = "debitAccountId")
    @Mapping(source = "transaction.creditAccount.id", target = "creditAccountId")
    TransactionDto toDto(Transaction transaction);

    Transaction toEntity(TransactionDto transactionDto);

    Transaction toEntity(CreateTransactionDto transactionDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    List<TransactionDto> transactionToTransactionDto(List<Transaction> transactionsList);

//    default Account stringAccount(String id){
//
//    }
}
