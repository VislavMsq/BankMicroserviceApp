package bankmicroservicesapp.exeption;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String e) {
        super(e);
    }
}
