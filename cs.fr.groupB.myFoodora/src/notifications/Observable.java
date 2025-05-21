package notifications;


/**
 * Observable interface for the Observer design pattern.
 * This interface will get implemented by the Meal class since it is the one observed, notifying its observers (customers).
 * 
 * @author Aymane ADIB
 */
public interface Observable {

    /**
     * Registers an observer to the observable.
     * 
     * @param observer The observer to be registered.
     */
    public void registerObserver(Observer observer);
    
    /**
     * Removes an observer from the observable.
     * 
     * @param observer The observer to be removed.
     */
    public void removeObserver(Observer observer);
    
    /**
     * Notifies all registered observers of a change in the observable.
     */
    public void notifyObservers();

}
