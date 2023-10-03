package bankmicroservicesapp.service;

import bankmicroservicesapp.dto.AgreementDto;

import java.util.List;
import java.util.UUID;

public interface AgreementService {
    void deleteById(String agreementId);

    List<AgreementDto> findAgreementWhereManagerId(UUID managerId);

    List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId);
}
