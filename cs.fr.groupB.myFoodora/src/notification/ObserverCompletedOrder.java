package notification;

import order.Order;

/**
 * Observer interface for the Observer design pattern.
 * This interface will get implemented by the Courier to be notified that there's a new Order to accept/refuse.
 * 
 * @author Alisson Bonatto
 */
public interface ObserverCompletedOrder {
    
    /**
     * Update method to be called when the observable notifies its observers.
     * 
     * @param 
     */
    public void update(Order order);

}
