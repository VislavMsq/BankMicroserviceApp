package bankmicroservicesapp.exeption;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String userNotExist) {
        super(userNotExist);
    }
}
