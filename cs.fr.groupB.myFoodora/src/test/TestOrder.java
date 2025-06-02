package test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
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
import order.BadOrderHandlingException;
import order.Order;
import user.BadUserCreationException;
import user.Courier;
import user.Customer;
import user.Location;
import user.Restaurant;

/**
 * Test class for Order class.
 * 
 * @author Alisson Bonatto
 */
public class TestOrder {

	static Customer customerLucas;
	static Courier courier1;
	static Restaurant restaurant1;
	
	@BeforeClass
	public static void initializeTests() throws BadUserCreationException {
		customerLucas = new Customer("CustomerTestOrder", "CustomerTestOrderSurname", "customertestorder", "1234",
				"+customertestorderphone", "customer.testoder@email.com", new Location(0, 0), true);
		courier1 = new Courier("CourierTestOrder", "CourierTestOrderSurname", "couriertestorder", "1234",
				"+customertestorderphone2", new Location(5, 9));
		restaurant1 = new Restaurant("RestaurantParis", "restoparisTestOrder", "1234", new Location(0.1, 0.1));
	}

	@AfterClass
	public static void tearDown() {
		customerLucas = null;
		courier1 = null;
		restaurant1 = null;
	}
	
	@Test
	public void testOrderConstructor() {
		Order order = new Order(customerLucas, restaurant1, courier1);
		Assert.assertTrue(order.getCourier().equals(courier1));
		Assert.assertTrue(order.getCustomer().equals(customerLucas));
		Assert.assertTrue(order.getRestaurant().equals(restaurant1));
		Assert.assertTrue(order.getDate().equals(LocalDate.now()));
	}
	
	@Test
	public void testSetters() {
		Order order = new Order(null, null, null);
		order.setCourier(courier1);
		order.setCustomer(customerLucas);
		order.setRestaurant(restaurant1);
		order.setDate(LocalDate.now().minusDays(1));
		order.setPrice(100);
		
		Assert.assertTrue(order.getCourier().equals(courier1));
		Assert.assertTrue(order.getCustomer().equals(customerLucas));
		Assert.assertTrue(order.getRestaurant().equals(restaurant1));
		Assert.assertTrue(order.getDate().equals(LocalDate.now().minusDays(1)));
		Assert.assertTrue(order.getPrice() == 100);
	}
	
	@Test
	public void testToString() {
		Order order = new Order(customerLucas, restaurant1, courier1);
		Assert.assertTrue(order.toString().equals("Order nº "+ order.getId() + " from " + restaurant1.getName() + " to " + customerLucas.getName()));
	}
	
	@Test
	public void testPriceComputation() throws BadMealFormulaException, UnrecognizedDishException, BadOrderHandlingException {
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
		Order order = new Order(customerLucas, restaurant1, courier1);
		order.addDish(dish1);
		order.addDish(dish2);
		order.addMeal(halfMeal);
		
		Assert.assertTrue(order.getPrice() == (100 + 200) * (1 + 0.95));
		
		// Getting meals and dishes from order
		List<Meal> mealsInOrder = order.getMeals();
		List<Dish> dishesInOrder = order.getDishes();
		
		Assert.assertTrue(mealsInOrder.contains(halfMeal));
		Assert.assertTrue(dishesInOrder.contains(dish1));
		Assert.assertTrue(dishesInOrder.contains(dish2));
		
		// Removing meals and dishes
		order.removeDish(dish1);
		order.removeDish(dish2);
		order.removeMeal(halfMeal);
		
		Assert.assertTrue(!order.getDishes().contains(dish1));
		Assert.assertTrue(!order.getDishes().contains(dish2));
		Assert.assertTrue(!order.getMeals().contains(halfMeal));
		Assert.assertTrue(order.getPrice() == 0);
	}
	
}
