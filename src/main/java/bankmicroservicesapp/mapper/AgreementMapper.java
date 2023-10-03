package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    @Mapping(source = "agreement.account.user.id", target = "userId")
    @Mapping(source = "agreement.product.productType", target = "productName")
    AgreementDto toDto(Agreement agreement);



    @Mapping(source = "interestRate", target = "interestRate", qualifiedByName = "toDouble")
    @Mapping(source = "discount", target = "discount", qualifiedByName = "toDouble")
    @Mapping(source = "agreementLimit", target = "agreementLimit", qualifiedByName = "toDouble")
    @Mapping(source = "sum", target = "sum", qualifiedByName = "toDouble")
    List<AgreementDto> agreementToAgreementDto(List<Agreement> managerId);

    @Named("toDouble")
    default Double toDoubleParse(String string) {
        return Double.parseDouble(string);
    }

    @Named("toUUID")
    default UUID toUUIDParse(String string) {
        return UUID.fromString(string);
    }

}
