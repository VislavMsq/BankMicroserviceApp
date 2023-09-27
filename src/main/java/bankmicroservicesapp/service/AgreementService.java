package bankmicroservicesapp.service;

import bankmicroservicesapp.entity.Agreement;

import java.util.List;

public interface AgreementService {
    void deleteById(String agreementId);

    List<Agreement> findAgreementWhereManagerId(String managerId);
}
