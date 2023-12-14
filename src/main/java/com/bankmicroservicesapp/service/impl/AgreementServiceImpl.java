package com.bankmicroservicesapp.service.impl;

import com.bankmicroservicesapp.dto.AgreementDto;
import com.bankmicroservicesapp.entity.Agreement;
import com.bankmicroservicesapp.exception.DataNotExistException;
import com.bankmicroservicesapp.exception.ErrorMessage;
import com.bankmicroservicesapp.mapper.AgreementMapper;
import com.bankmicroservicesapp.repository.AgreementRepository;
import com.bankmicroservicesapp.repository.EmployeeRepository;
import com.bankmicroservicesapp.repository.UserRepository;
import com.bankmicroservicesapp.service.AgreementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final EmployeeRepository employeeRepository;


    public AgreementServiceImpl(AgreementRepository agreementRepository, AgreementMapper agreementMapper, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.agreementRepository = agreementRepository;
        this.agreementMapper = agreementMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteById(String agreementId) {
        if (agreementRepository.existsById(UUID.fromString(agreementId))) {
            agreementRepository.deleteById(UUID.fromString(agreementId));
            return;
        }
        throw new DataNotExistException(ErrorMessage.DATA_NOT_EXIST);
    }

    @Override
    @Transactional
    public List<AgreementDto> findAgreementWhereManagerId(UUID managerId) {
        List<Agreement> agreementList = employeeRepository.findAgreementByManagerId(managerId);
        return agreementMapper.agreementToAgreementDto(agreementList);
    }

    @Override
    @Transactional
    public List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId) {
        List<Agreement> agreements = agreementRepository.findAgreementsClientIdIs(clientId);
        return agreementMapper.agreementToAgreementDto(agreements);
    }

    @Override
    public AgreementDto findAgreementById(UUID id) {
        return agreementMapper.toDto(agreementRepository.findById(id).orElseThrow(
                () -> new DataNotExistException(ErrorMessage.DATA_NOT_EXIST)));
    }
}
