package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import user.Courier;
import user.CourierComparator;
import user.Customer;
import user.Restaurant;

/**
 * Delivery strategy that selects the courier with the least number of deliveries
 * who is currently on duty, promoting a fair distribution of workload among couriers.
 * 
 * @author Alisson Bonatto
 */
public class FairOccupationDelivery implements DeliveryStrategy {

    /**
     * Selects the least occupied courier who is on duty from the provided set of couriers.
     * Couriers are sorted by their number of deliveries in ascending order,
     * and the first courier on duty in this order is selected.
     * 
     * @param couriers the couriers
     * @param restaurant the restaurant from which the order will be picked up (not used in this strategy)
     * @param customer the customer who will receive the order (not used in this strategy)
     * @return the selected Courier with the least deliveries who is on duty, or null if none available
     */
    @Override
    public ArrayList<Courier> selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer) {
        // Creating a comparator and sorting couriers by number of deliveries
        CourierComparator comparator = new CourierComparator();
        ArrayList<Courier> arrayCouriers = new ArrayList<Courier>(couriers);
        Collections.sort(arrayCouriers, comparator);
        
        for (int i = 0; i < arrayCouriers.size(); i++) {
        	if (!arrayCouriers.get(i).isOnDuty()) arrayCouriers.remove(arrayCouriers.get(i));
        }

        return arrayCouriers;
    }

}
