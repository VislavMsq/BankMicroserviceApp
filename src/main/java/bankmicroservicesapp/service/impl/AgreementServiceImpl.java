package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.helper.ValidUUID;
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
    public boolean deleteById(String agreementId) throws InvalidIdException {
        if (!ValidUUID.validationUUID(agreementId)) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
        if (agreementRepository.existsById(UUID.fromString(agreementId))) {
            agreementRepository.deleteById(UUID.fromString(agreementId));
            return true;
        }
        return false;
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
        List<Agreement> agreements = userRepository.findAgreementsClientIdIs(clientId);
        return agreementMapper.agreementToAgreementDto(agreements);
    }
}
