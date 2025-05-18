package order;

/**
 * Exception class for handling errors related to order processing.
 * This exception is thrown when there are issues with processing an order.
 * 
 * @author Aymane Adib
 */
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
