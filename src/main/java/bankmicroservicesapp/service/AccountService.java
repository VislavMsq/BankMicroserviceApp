package bankmicroservicesapp.service;

import bankmicroservicesapp.entity.Account;

public interface AccountService {

    Account getById(String cardId);

}
