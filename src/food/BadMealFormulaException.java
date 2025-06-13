package food;

/**
 * Exception class for handling errors related to meal creation.
 * This exception is thrown when a meal formula doesn't corresponds to the correct one.
 * 
 * @author Alisson Bonatto
 */
public class BadMealFormulaException extends Exception{

	private static final long serialVersionUID = -8895293105188966530L;
	
	public BadMealFormulaException(String message) {
        super(message);
    }

    public BadMealFormulaException(String message, Throwable cause) {
        super(message, cause);
    }

}
