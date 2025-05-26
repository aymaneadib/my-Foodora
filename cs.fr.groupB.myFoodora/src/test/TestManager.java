package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import system.FairOccupationDelivery;
import system.MyFoodora;
import system.UserNotFoundException;
import users.BadUserCreationException;
import users.Courier;
import users.Customer;
import users.Location;
import users.Manager;
import users.Restaurant;

public class TestManager {

	static Manager manager1;
	static Customer customerLucas;
	static Customer customerTheo;
	static Courier courier1;
	static Courier courier2;
	static Restaurant restaurant1;
	static Restaurant restaurant2;
	static MyFoodora system;
	
	@BeforeClass
	static public void initializeTests() throws BadUserCreationException {
		manager1 = new Manager("Alisson", "Bonatto", "alissonbonatto", "1234");
		customerLucas = new Customer("Lucas", "Petit", "lucaspetit", "1234", "+331", "lucas.petit@email.com", new Location(0, 0));
		customerTheo = new Customer("Theo", "Bernard", "theobernard", "abcd", "+332", "theo.bernard@email.com", new Location(1, 0), true);
		courier1 = new Courier("Bernard", "Petit", "bernardpetit", "1234", "+333", new Location(5, 9));
		courier2 = new Courier("Maria", "G.", "mariag", "1234", "+334", new Location(5, 9));
		restaurant1 = new Restaurant("RestaurantParis", "restoparis", "1234", new Location(0.1, 0.1));
		restaurant2 = new Restaurant("RestaurantNice", "restonice", "1234", new Location(0.1, 0.1));
		system = MyFoodora.getInstance();
		system.addUser(courier1);
		system.addUser(courier2);
		system.addUser(customerTheo);
		system.addUser(customerLucas);
		system.addUser(manager1);
		system.addUser(restaurant1);
		system.addUser(restaurant2);
	}
	
	@Test
	public void testManagerConstructor() {
		Assert.assertTrue(manager1.getName().equals("Alisson"));
		Assert.assertTrue(manager1.getSurname().equals("Bonatto"));
		Assert.assertTrue(manager1.getUsername().equals("alissonbonatto"));
		Assert.assertTrue(manager1.getPassword().equals("1234"));
		Assert.assertTrue(manager1.isActive() == true);
	}
	
	@Test
	public void testManagerSetters() throws BadUserCreationException {
		Manager managerTest = new Manager("", "", "", "");
		managerTest.setName("Bob");
		managerTest.setSurname("Dylan");
		managerTest.setUsername("bobdylan");
		managerTest.setPassword("123456789");
		managerTest.deactivateUser();
		managerTest.setDeliveryCost(20, system);
		managerTest.setMarkupPercentage(0.1, system);
		managerTest.setServiceFee(0.3, system);
		managerTest.setDeliveryStrategy(system, new FairOccupationDelivery());
		
		Assert.assertTrue(managerTest.getName().equals("Bob"));
		Assert.assertTrue(managerTest.getSurname().equals("Dylan"));
		Assert.assertTrue(managerTest.getUsername().equals("bobdylan"));
		Assert.assertTrue(managerTest.getPassword().equals("123456789"));
		Assert.assertTrue(managerTest.isActive() == false);
		Assert.assertTrue(system.getProfitData().getDeliveryCost() == 20);
		Assert.assertTrue(system.getProfitData().getMarkupPercentage() == 0.1);
		Assert.assertTrue(system.getProfitData().getServiceFee() == 0.3);
		Assert.assertTrue(system.getDeliveryStrategy() instanceof FairOccupationDelivery);
	}
	
	@Test
	public void testAddAndRemoveUser() throws BadUserCreationException, UserNotFoundException {
		Assert.assertTrue(system.getCustomers().contains(customerTheo));
		Assert.assertTrue(system.getCustomers().contains(customerLucas));
		
		Customer newCustomer = new Customer("NewCustomer", "A.", "newcustomer", "1234", "+335", "newcustomer@email.com", new Location(0, 0));
		manager1.addUser(newCustomer, system);
		Assert.assertTrue(system.getCustomers().contains(newCustomer));
		
		manager1.removeUser(newCustomer, system);
		Assert.assertTrue(!system.getCustomers().contains(newCustomer));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testRemoveUserUserNotFoundException() throws BadUserCreationException, UserNotFoundException {
		Customer newCustomer = new Customer("", "", "usernotinthesystem", "1234", "+336", "notinsystemuser@email.com", new Location(0, 0));
		manager1.removeUser(newCustomer, system);
	}
	
	@Test
	public void testGetMostAndLeastSellingRestaurants() {
		restaurant1.setOrderCounter(15);
		restaurant2.setOrderCounter(10);
		Assert.assertTrue(manager1.mostSellingRestaurant(system).equals(restaurant1));
		Assert.assertTrue(manager1.leastSellingRestaurant(system).equals(restaurant2));
	}
	
	@Test
	public void testGetMostAndLeastActiveCourier() {
		courier1.setDeliveryCounter(10);
		courier2.setDeliveryCounter(100);
		Assert.assertTrue(manager1.mostActiveCourier(system).equals(courier2));
		Assert.assertTrue(manager1.leastActiveCourier(system).equals(courier1));
	}
	
	
}
