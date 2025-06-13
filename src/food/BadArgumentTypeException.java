package food;

/**
 * Exception class for handling errors related to factories.
 * This exception is thrown when a bad argument is passed to a factory.
 * 
 * @author Alisson Bonatto
 */
public class BadArgumentTypeException extends Exception{


	private static final long serialVersionUID = -643932340116799550L;

	public BadArgumentTypeException(String message) {
        super(message);
    }

    public BadArgumentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
