package bankmicroservicesapp.exeption;

public class UserNotExistException extends Throwable {
    public UserNotExistException(String userNotExist) {
        super(userNotExist);
    }
}
