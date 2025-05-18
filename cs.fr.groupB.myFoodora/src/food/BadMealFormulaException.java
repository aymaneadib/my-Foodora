package food;

public class BadMealFormulaException extends Exception{

	private static final long serialVersionUID = -8895293105188966530L;
	
	public BadMealFormulaException(String message) {
        super(message);
    }

    public BadMealFormulaException(String message, Throwable cause) {
        super(message, cause);
    }

}
