package food;

public class BadArgumentTypeException extends Exception{


	private static final long serialVersionUID = -643932340116799550L;

	public BadArgumentTypeException(String message) {
        super(message);
    }

    public BadArgumentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
