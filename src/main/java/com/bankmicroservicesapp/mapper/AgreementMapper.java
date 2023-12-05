package com.bankmicroservicesapp.mapper;

import com.bankmicroservicesapp.dto.AgreementDto;
import com.bankmicroservicesapp.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    @Mapping(source = "account.user.id", target = "userId")
    @Mapping(source = "product.productType", target = "productName")
    AgreementDto toDto(Agreement agreement);

    @Mapping(source = "interestRate", target = "interestRate")
    @Mapping(source = "discount", target = "discount")
    @Mapping(source = "agreementLimit", target = "agreementLimit")
    @Mapping(source = "sum", target = "sum")
    List<AgreementDto> agreementToAgreementDto(List<Agreement> managerId);

}
