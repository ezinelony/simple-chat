package exceptions;


public class ConstraintViolationException extends Throwable {
    public ConstraintViolationException() {
        super("You have supplied values that violate our constraints");
    }

    public ConstraintViolationException(String message) {
        super(message);
    }
}
