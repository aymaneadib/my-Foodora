package system;

/**
 * Exception thrown when no available courier is found for a delivery request.
 * 
 * @author Alisson Bonatto
 */
public class AvailableCourierNotFoundException extends Exception {

    private static final long serialVersionUID = 2455407000631728881L;

    /**
     * Constructs a new AvailableCourierNotFoundException with the specified detail message.
     * 
     * @param message the detail message explaining the exception
     */
    public AvailableCourierNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new AvailableCourierNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the exception
     * @param cause the cause of this exception
     */
    public AvailableCourierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
