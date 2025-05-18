package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import users.Courier;
import users.CourierComparator;
import users.Restaurant;

public class FairOccupationDelivery implements DeliveryStrategy {

	@Override
	public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant) {
		CourierComparator comparator = new CourierComparator();
		ArrayList<Courier> arrayCouriers = new ArrayList<Courier>(couriers);
		
		Collections.sort(arrayCouriers, comparator);
		
		return arrayCouriers.get(0);
	}

}
