package system;

/**
 * Exception thrown when a user is not found in the system.
 * 
 * @author Alisson Bonatto
 */
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 2565479115146193516L;

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of this exception
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
