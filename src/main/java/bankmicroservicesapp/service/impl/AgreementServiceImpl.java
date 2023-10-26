package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.controller.util.Valid;
import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.exeption.UserNotExistException;
import bankmicroservicesapp.mapper.AgreementMapper;
import bankmicroservicesapp.repository.AgreementRepository;
import bankmicroservicesapp.repository.EmployeeRepository;
import bankmicroservicesapp.repository.UserRepository;
import bankmicroservicesapp.service.AgreementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;


    public AgreementServiceImpl(AgreementRepository agreementRepository, AgreementMapper agreementMapper, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.agreementRepository = agreementRepository;
        this.agreementMapper = agreementMapper;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void deleteById(String agreementId) {
        if (!Valid.isValidUUID(agreementId)) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
        if (agreementRepository.existsById(UUID.fromString(agreementId))) {
            agreementRepository.deleteById(UUID.fromString(agreementId));
        }
        throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
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
        System.out.println(agreements + "0000000000000000000000000000000");
        return agreementMapper.agreementToAgreementDto(agreements);
    }
}
