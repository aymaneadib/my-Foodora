package system;

import java.util.Set;

import users.Courier;
import users.Customer;
import users.Restaurant;

/**
 * Delivery strategy that selects the courier who can deliver the order fastest,
 * based on the total distance from the courier to the restaurant plus the distance
 * from the restaurant to the customer. Only couriers who are on duty are considered.
 * 
 * @author Alisson Bonatto
 */
public class FastestDelivery implements DeliveryStrategy {

    /**
     * Selects the courier with the minimum total delivery distance (courier to restaurant
     * plus restaurant to customer) among those who are on duty.
     * 
     * @param couriers the couriers
     * @param restaurant the restaurant from which the order will be picked up
     * @param customer the customer who will receive the order
     * @return the selected Courier with the shortest delivery path and on duty,
     *         or null if no courier is on duty
     */
    @Override
    public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer) {
        Courier fastestCourier = null;
        double minDistance = Double.POSITIVE_INFINITY;

        // Find courier with minimum total distance who is on duty
        for (Courier courier : couriers) {
            if (courier.isOnDuty()) {
                double totalDistance = restaurant.getLocation().distanceTo(courier.getPosition())
                                     + restaurant.getLocation().distanceTo(customer.getAdress());
                if (totalDistance < minDistance) {
                    fastestCourier = courier;
                    minDistance = totalDistance;
                }
            }
        }

        return fastestCourier;
    }
}
