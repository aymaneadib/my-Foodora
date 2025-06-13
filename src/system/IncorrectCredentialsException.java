package system;

/**
 * Exception thrown when username and password do not match.
 * 
 * @author Alisson Bonatto
 */
public class IncorrectCredentialsException extends Exception {

    private static final long serialVersionUID = 1171147363304793519L;

    /**
     * Constructs a new IncorrectCredentialsException with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public IncorrectCredentialsException(String message) {
        super(message);
    }

    /**
     * Constructs a new IncorrectCredentialsException with the specified detail message
     * and cause.
     * 
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of this exception
     */
    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
