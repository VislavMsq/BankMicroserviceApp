package bankmicroservicesapp.exeption;

public class InvalidStatusException extends Exception {
    public InvalidStatusException(String e) {
        super(e);
    }
}
