package food;

/**
 * Exception class for handling errors related to dish creation.
 * This exception is thrown when someone tries to create a unrecognized type of dish.
 * 
 * @author Alisson Bonatto
 */
public class BadDishTypeCreationException extends Exception{

	
	private static final long serialVersionUID = 7923272910602834029L;

	public BadDishTypeCreationException(String message) {
        super(message);
    }

    public BadDishTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}