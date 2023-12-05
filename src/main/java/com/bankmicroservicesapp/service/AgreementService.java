package com.bankmicroservicesapp.service;

import com.bankmicroservicesapp.dto.AgreementDto;
import com.bankmicroservicesapp.exeption.InvalidIdException;

import java.util.List;
import java.util.UUID;

public interface AgreementService {
    void deleteById(String agreementId) throws InvalidIdException;

    List<AgreementDto> findAgreementWhereManagerId(UUID managerId);

    List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId);

    AgreementDto findAgreementById(UUID id);

}
