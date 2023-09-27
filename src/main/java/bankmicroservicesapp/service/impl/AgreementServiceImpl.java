package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.mapper.AgreementMapper;
import bankmicroservicesapp.repository.AgreementRepository;
import bankmicroservicesapp.service.AgreementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;

    public AgreementServiceImpl(AgreementRepository agreementRepository, AgreementMapper agreementMapper) {
        this.agreementRepository = agreementRepository;
        this.agreementMapper = agreementMapper;
    }

    @Override
    public void deleteById(String agreementId) throws NumberFormatException {
        agreementRepository.deleteById(UUID.fromString(agreementId));
    }

    @Override
    public List<Agreement> findAgreementWhereManagerId(String managerId) {

        return null;
    }
}
