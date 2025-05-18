package food;

public class UnrecognizedDishException extends Exception{

	private static final long serialVersionUID = 9078637454546240062L;

	public UnrecognizedDishException(String message) {
        super(message);
    }

    public UnrecognizedDishException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
