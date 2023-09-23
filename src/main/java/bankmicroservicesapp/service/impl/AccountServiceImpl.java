package bankmicroservicesapp.service.impl;

import bankmicroservicesapp.entity.Account;
import bankmicroservicesapp.repository.AccountRepository;
import bankmicroservicesapp.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service // указывает спрингу, что это бин и чтобы
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional // анатация для транзы. все обращения к бд должны быть с анатацией
    @Override
    public Account getById(String cardId) {
        return accountRepository.findById(UUID.fromString(cardId)).orElse(null); // закастил ююд до стрига
    }
}
