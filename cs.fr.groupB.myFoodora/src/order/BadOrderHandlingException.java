package order;

public class BadOrderHandlingException extends Exception {
    public BadOrderHandlingException(String message) {
        super(message);
    }

    public BadOrderHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadOrderHandlingException(Throwable cause) {
        super(cause);
    }

}
