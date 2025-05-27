package system;

import java.util.Set;

import user.Courier;
import user.Customer;
import user.Restaurant;

/**
 * Interface defining the strategy for selecting a courier for a delivery.
 * Different implementations can provide different selection algorithms.
 * 
 * @author Alisson Bonatto
 */
public interface DeliveryStrategy {

    /**
     * Selects an appropriate courier from the given set of couriers to deliver
     * an order from the specified restaurant to the specified customer.
     * 
     * @param couriers the set of couriers
     * @param restaurant the restaurant from which the order is picked up
     * @param customer the customer who will receive the order
     * @return the selected Courier for the delivery
     */
    public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer);
    
}
