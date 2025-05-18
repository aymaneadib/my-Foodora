package system;

import java.util.Set;

import users.Courier;
import users.Customer;
import users.Restaurant;

public class FastestDelivery implements DeliveryStrategy {

	@Override
	public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer) {
		Courier fastestCourier = null;
		double minDistance = Double.POSITIVE_INFINITY;
		
		// Gets the courier with the min distance to restaurant location
		for (Courier courier : couriers) {
			double totalDistance = restaurant.getLocation().distanceTo(courier.getPosition());
			totalDistance += restaurant.getLocation().distanceTo(customer.getAdress());
			
			if(totalDistance < minDistance) {
				fastestCourier = courier;
				minDistance = totalDistance;
			}
		}
		
		// Selecting fastest courier that is on duty
		for(Courier courier : couriers) {
			if(courier.isOnDuty()) {
				fastestCourier = courier;
				break;
			}
		}
		
		return fastestCourier;
	}
}
