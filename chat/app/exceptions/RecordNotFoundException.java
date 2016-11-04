package exceptions;


public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException() {
        super("Record cannot be found");
    }
}
