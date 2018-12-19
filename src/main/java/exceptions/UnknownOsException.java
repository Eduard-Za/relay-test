package exceptions;

public class UnknownOsException extends RuntimeException {
    public UnknownOsException(String message) {
        super(message);
    }
}
