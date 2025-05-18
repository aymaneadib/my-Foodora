package food;

public class BadMealTypeCreationException extends Exception{

	private static final long serialVersionUID = -5729756277448893599L;

	public BadMealTypeCreationException(String message) {
        super(message);
    }

    public BadMealTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
