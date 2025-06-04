package system;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import user.Courier;
import user.Customer;
import user.Restaurant;

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
    public ArrayList<Courier> selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer) {
    	ArrayList<Double> couriersDistances = new ArrayList<Double>();
    	ArrayList<Courier> orderedCouriers = new ArrayList<Courier>();
    	ArrayList<Courier> listOfCouriers = new ArrayList<>(couriers);

        // Calculate distances
        for (Courier courier : couriers) {
            double totalDistance = restaurant.getLocation().distanceTo(courier.getPosition())
                                 + restaurant.getLocation().distanceTo(customer.getAdress());
            couriersDistances.add(totalDistance);
        }
        
        // Creating a list of indexes
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < listOfCouriers.size(); i++) {
            indices.add(i);
        }
        
        // Sorting indexes by distances
        indices.sort(Comparator.comparingDouble(index -> couriersDistances.get(index)));
        for (int sortedIndex : indices) {
            orderedCouriers.add(listOfCouriers.get(sortedIndex));
        }
        
        // Excluding not onDuty Courier
        for (Courier courier : orderedCouriers) {
        	if (!courier.isOnDuty()) orderedCouriers.remove(courier);
        }

        return orderedCouriers;
    }
}
