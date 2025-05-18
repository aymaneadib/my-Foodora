package food;

/**
 * Exception thrown when a Dish type is not recognized or supported.
 * 
 * @author Alisson Bonatto
 */
public class UnrecognizedDishException extends Exception {

    private static final long serialVersionUID = 9078637454546240062L;

    /**
     * Constructs a new UnrecognizedDishException with the specified detail message.
     * 
     * @param message the detail message explaining the exception
     */
    public UnrecognizedDishException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnrecognizedDishException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the exception
     * @param cause the cause of this exception
     */
    public UnrecognizedDishException(String message, Throwable cause) {
        super(message, cause);
    }

}
