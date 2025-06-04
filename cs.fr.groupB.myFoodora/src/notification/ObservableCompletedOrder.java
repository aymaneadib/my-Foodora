package notification;

/**
 * Observer interface for the Observer design pattern.
 * This interface will get implemented by the Order to notify the Couriers that there's a new Order to accept/refuse.
 * 
 * @author Alisson Bonatto
 */

public interface ObservableCompletedOrder {
	
	/**
     * Removes an observer from the observable.
     * 
     * @param observer The observer to be removed.
     */
    public void removeObserver(ObserverCompletedOrder observer);
    
    /**
     * Notifies all registered observers of a change in the observable.
     */
    public void notifyObservers();

}
