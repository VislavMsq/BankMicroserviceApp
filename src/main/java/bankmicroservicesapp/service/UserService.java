package bankmicroservicesapp.service;

import bankmicroservicesapp.entity.User;

public interface UserService {
    User getById(String userId);
}
