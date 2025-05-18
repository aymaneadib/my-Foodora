package food;

/**
 * Exception class for handling errors related to meal creation.
 * This exception is thrown when someone tries to create a unrecognized type of meal.
 * 
 * @author Alisson Bonatto
 */
public class BadMealTypeCreationException extends Exception{

	private static final long serialVersionUID = -5729756277448893599L;

	public BadMealTypeCreationException(String message) {
        super(message);
    }

    public BadMealTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
