package system;

import java.util.Set;

import users.Courier;
import users.Restaurant;

public class FastestDelivery implements DeliveryStrategy {

	@Override
	public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant) {
		Courier fastestCourier = null;
		double minDistance = Double.POSITIVE_INFINITY;
		
		// Gets the courier with the min distance to restaurant location
		for (Courier courier : couriers) {
			double currentDistance = restaurant.getLocation().distanceTo(courier.getPosition());
			
			if(currentDistance < minDistance) {
				fastestCourier = courier;
				minDistance = currentDistance;
			}
		}
		
		return fastestCourier;
	}
}
