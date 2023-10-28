package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.exeption.InvalidIdException;

import java.util.List;
import java.util.UUID;

public interface AgreementService {
    void deleteById(String agreementId) throws InvalidIdException;

    List<AgreementDto> findAgreementWhereManagerId(UUID managerId);

    List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId);

    AgreementDto findAgreementById(UUID id);

}
