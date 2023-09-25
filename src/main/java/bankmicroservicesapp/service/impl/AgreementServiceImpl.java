package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.repository.AgreementRepository;
import bankmicroservicesapp.service.AgreementService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public void deleteById(String agreementId) throws NumberFormatException {
        agreementRepository.deleteById(UUID.fromString(agreementId));
    }
}
