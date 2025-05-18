package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import users.Courier;
import users.CourierComparator;
import users.Customer;
import users.Restaurant;

public class FairOccupationDelivery implements DeliveryStrategy {

	@Override
	public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer) {
		// Creating a comparator and sorting couriers by number of deliveries
		CourierComparator comparator = new CourierComparator();
		ArrayList<Courier> arrayCouriers = new ArrayList<Courier>(couriers);
		Collections.sort(arrayCouriers, comparator);
		
		// Selecting least occupied courier that is on duty
		Courier selectedCourier = null;
		
		for(Courier courier : couriers) {
			if(courier.isOnDuty()) {
				selectedCourier = courier;
				break;
			}
		}
		
		return selectedCourier;
	}

}
