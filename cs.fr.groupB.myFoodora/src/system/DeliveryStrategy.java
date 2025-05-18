package system;

import java.util.Set;

import users.Courier;
import users.Customer;
import users.Restaurant;

public interface DeliveryStrategy {

	public Courier selectCourier(Set<Courier> couriers, Restaurant restaurant, Customer customer);
	
}
