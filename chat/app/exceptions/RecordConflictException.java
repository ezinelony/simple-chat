package exceptions;


public class RecordConflictException extends Exception {
    public RecordConflictException() {
        super("The identifier for this record conflicts with another");
    }

    public RecordConflictException(String message) {
        super(message);
    }
}
