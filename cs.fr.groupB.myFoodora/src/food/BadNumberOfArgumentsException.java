package food;

/**
 * Exception class for handling errors related to factories.
 * This exception is thrown when someone pass an incorrect number of arguments
 * to the factory.
 * 
 * @author Alisson Bonatto
 */
public class BadNumberOfArgumentsException extends Exception{

	private static final long serialVersionUID = -4844028700398490354L;

	public BadNumberOfArgumentsException(String message) {
        super(message);
    }

    public BadNumberOfArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

}
