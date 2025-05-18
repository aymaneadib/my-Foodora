package food;

public class BadDishTypeCreationException extends Exception{

	
	private static final long serialVersionUID = 7923272910602834029L;

	public BadDishTypeCreationException(String message) {
        super(message);
    }

    public BadDishTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}