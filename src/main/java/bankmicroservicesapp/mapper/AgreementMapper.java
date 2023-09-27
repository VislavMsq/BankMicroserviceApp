package bankmicroservicesapp.mapper;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    @Mapping(source = "agreement.manager.id", target = "managerId") // не нужна ;
    AgreementDto toDto(Agreement agreement);
}
