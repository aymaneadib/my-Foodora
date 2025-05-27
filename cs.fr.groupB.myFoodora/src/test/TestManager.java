package test;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import food.BadMealFormulaException;
import food.Dessert;
import food.Dish;
import food.HalfMeal;
import food.MainDish;
import food.Meal;
import food.UnrecognizedDishException;
import system.AvailableCourierNotFoundException;
import system.FairOccupationDelivery;
import system.IncorrectCredentialsException;
import system.MyFoodora;
import system.UserNotFoundException;
import user.BadUserCreationException;
import user.Courier;
import user.Customer;
import user.Location;
import user.Manager;
import user.Restaurant;
import user.UserFactory;

/**
 * Test class for Manager class.
 * 
 * @author Alisson Bonatto
 */
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
		customerLucas = new Customer("Lucas", "Petit", "lucaspetit", "1234", "+331", "lucas.petit@email.com", new Location(0, 0), true);
		customerTheo = new Customer("Theo", "Bernard", "theobernard", "abcd", "+332", "theo.bernard@email.com", new Location(1, 0), true);
		courier1 = new Courier("Bernard", "Petit", "bernardpetit", "1234", "+333", new Location(5, 9));
		courier1.setOnDuty(true);
		courier2 = new Courier("Maria", "G.", "mariag", "1234", "+334", new Location(5, 9));
		courier2.setOnDuty(true);
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
	
	@Test
	public void testUserFactoryCreateManager() throws BadUserCreationException {
		UserFactory userFactory = new UserFactory();
		Manager newManager = (Manager) userFactory.createUser("Manager", "ManagerName", "ManagerSurname", 
				"ManagerUser", "ManagerPassword");
		
		Assert.assertTrue(newManager.getName().equals("ManagerName"));
		Assert.assertTrue(newManager.getSurname().equals("ManagerSurname"));
		Assert.assertTrue(newManager.getUsername().equals("ManagerUser"));
		Assert.assertTrue(newManager.getPassword().equals("ManagerPassword"));
		Assert.assertTrue(newManager.isActive() == true);
	}
	
	@Test
	public void getTotalIncomeAndProfit() throws AvailableCourierNotFoundException, BadMealFormulaException,
			UnrecognizedDishException, UserNotFoundException, IncorrectCredentialsException {
		// Adding dishes and meals to restaurant1 menu
		MainDish dish1 = new MainDish("Dish1", 100, true, false);
		Dessert dish2 = new Dessert("Dish2", 200, false, false);
		HashSet<Dish> dishes = new HashSet<Dish>();
		dishes.add(dish1);
		dishes.add(dish2);
		HalfMeal halfMeal = new HalfMeal("HalfMeal1", dishes);
		HashSet<Meal> meals = new HashSet<Meal>();
		meals.add(halfMeal);
		restaurant1.addDish(dish1);
		restaurant1.addDish(dish2);
		restaurant1.addMeal(halfMeal);
		
		// customerLucas makes an order at restaurant1
		system.login("lucaspetit", "1234");
		system.makeOrder(customerLucas, restaurant1, dishes, meals);
		
		// customerTheo makes an order at restaurant1
		system.login("theobernard", "abcd");
		system.makeOrder(customerTheo, restaurant1, new HashSet<Dish>(), meals);
		
		// Comparing expected total income and profit with the return of the system
		// considering a error of 1% (due to imprecision in calculations)
		double expectedIncome = 100 + 200 + 2*(100+200)*0.95 + system.getProfitData().getServiceFee()*2;
		double totalIncomeReturnedBySystem = manager1.computeTotalIncome(system, LocalDate.now().minusMonths(1), LocalDate.now());
		Assert.assertTrue(totalIncomeReturnedBySystem >= expectedIncome*0.99 && totalIncomeReturnedBySystem <= expectedIncome*1.01);
		
		double expectedTotalProfit = (100 + 200 + 2*(100+200)*0.95)*system.getProfitData().getMarkupPercentage()
				+ system.getProfitData().getServiceFee()*2 - system.getProfitData().getDeliveryCost()*2;
		double totalProfitReturnedBySystem = manager1.computeTotalProfit(system, LocalDate.now().minusMonths(1), LocalDate.now());
		Assert.assertTrue(totalProfitReturnedBySystem >= expectedTotalProfit*0.99 && totalProfitReturnedBySystem <= expectedTotalProfit*1.01);
	}
	
	@Test
	public void testToString() {
		String expectedString = "Manager " + manager1.getSurname() + " ( "+ manager1.getId() +" )";
		Assert.assertTrue(manager1.toString().equals(expectedString));
	}
	
	
}
