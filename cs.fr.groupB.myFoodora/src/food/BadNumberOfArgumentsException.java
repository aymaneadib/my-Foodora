package food;

public class BadNumberOfArgumentsException extends Exception{

	private static final long serialVersionUID = -4844028700398490354L;

	public BadNumberOfArgumentsException(String message) {
        super(message);
    }

    public BadNumberOfArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

}
