package test;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fidelity.PointCard;
import food.BadMealFormulaException;
import food.Dessert;
import food.Dish;
import food.HalfMeal;
import food.MainDish;
import food.Meal;
import food.Menu;
import food.UnrecognizedDishException;
import order.Order;
import user.BadUserCreationException;
import user.Courier;
import user.Customer;
import user.Location;
import user.Restaurant;
import user.UserFactory;


/**
 * Test class for Restaurant class.
 * 
 * @author Alisson Bonatto
 */
public class TestRestaurant {
	
	static Restaurant restaurant1;

	@BeforeClass
	public static void initializeTests() throws BadUserCreationException{
		restaurant1 = new Restaurant("RestaurantParis", "restoparis", "1234", new Location(0.1, 0.1));
	}
	
	@Test
	public void testRestaurantConstructor() {
		Assert.assertTrue(restaurant1.getName().equals("RestaurantParis"));
		Assert.assertTrue(restaurant1.getUsername().equals("restoparis"));
		Assert.assertTrue(restaurant1.getPassword().equals("1234"));
		Assert.assertTrue(restaurant1.getLocation().equals(new Location(0.1, 0.1)));
		Assert.assertTrue(restaurant1.isActive() == true);
	}
	
	@Test
	public void testAddMealsAndDishes() throws BadMealFormulaException, UnrecognizedDishException {
		MainDish dish1 = new MainDish("Dish1", 100, true, false);
		Dessert dish2 = new Dessert("Dish2", 200, false, false);
		HashSet<Dish> dishes =  new HashSet<Dish>();
		dishes.add(dish1);
		dishes.add(dish2);
		HalfMeal halfMeal = new HalfMeal("HalfMeal1", dishes);
		restaurant1.addDish(dish1);
		restaurant1.addDish(dish2);
		restaurant1.addMeal(halfMeal);
		
		// Sets to use in asserts
		HashSet<HalfMeal> setOfMeals = new HashSet<HalfMeal>();
		setOfMeals.add(halfMeal);
		HashSet<MainDish> setOfMainDishes = new HashSet<MainDish>();
		setOfMainDishes.add(dish1);
		HashSet<Dessert> setOfDesserts = new HashSet<Dessert>();
		setOfDesserts.add(dish2);
		
		Assert.assertTrue(restaurant1.getMenu().getMeals().equals(setOfMeals));
		Assert.assertTrue(restaurant1.getMenu().getMainDishes().equals(setOfMainDishes));
		Assert.assertTrue(restaurant1.getMenu().getDesserts().equals(setOfDesserts));
		
		restaurant1.removeDish(dish1);
		restaurant1.removeDish(dish2);
		restaurant1.removeMeal(halfMeal);
		
		Assert.assertTrue(restaurant1.getMenu().getMeals().equals(new HashSet<Meal>()));
		Assert.assertTrue(restaurant1.getMenu().getMainDishes().equals(new HashSet<MainDish>()));
		Assert.assertTrue(restaurant1.getMenu().getDesserts().equals(new HashSet<Dessert>()));
	}
	
	@Test
	public void testGetPriceDifferentFidelityCards() throws BadUserCreationException {
		Customer customer = new Customer("", "", "customer1", "", "+331", "", new Location(0, 0));
		Courier courier = new Courier("", "", "courier1", "", "+332", new Location(0, 0));
		MainDish dish1 = new MainDish("Dish1", 100, true, false);
		
		Order order = new Order(customer, restaurant1, courier);
		order.addDish(dish1);
		
		Assert.assertTrue(restaurant1.getPrice(order, customer) == 100);
		
		PointCard pointCard = new PointCard(customer);
		customer.setFidelityCard(pointCard);
		pointCard.addMoneySpent(1000);
		
		Assert.assertTrue(restaurant1.getPrice(order, customer) == 90);
		
		restaurant1.incrementOrderCounter();
		
		Assert.assertTrue(restaurant1.getOrderCounter() == 1);
	}
	
	@Test
	public void testGetAndSetDiscounts() {
		restaurant1.setGeneralDiscount(0.5);
		restaurant1.setSpecialDiscount(0.8);
		
		Assert.assertTrue(restaurant1.getGeneralDiscount() == 0.5);
		Assert.assertTrue(restaurant1.getSpecialDiscount() == 0.8);
		
		restaurant1.setGeneralDiscount(0.05);
		restaurant1.setSpecialDiscount(0.1);
	}
	
	@Test
	public void testToString() {
		String expectedString = "Restaurant "+ restaurant1.getName() +" "+ restaurant1.getLocation();
		
		Assert.assertTrue(restaurant1.toString().equals(expectedString));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testUserAlreadyUsed() throws BadUserCreationException {
		@SuppressWarnings("unused")
		Restaurant restaurant2 =  new Restaurant("RestaurantMarseille", "restoparis", "1234", new Location(0.1, 0.1));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testUserAlreadyUsedViaSetter() throws BadUserCreationException {
		Restaurant restaurant2 =  new Restaurant("RestaurantMarseille", "resto", "1234", new Location(0.1, 0.1));
		restaurant2.setUsername("restoparis");
	}
	
	@Test
	public void testSetMenu() {
		Menu emptyMenu = new Menu();
		restaurant1.setMenu(emptyMenu);
		
		Assert.assertTrue(restaurant1.getMenu().equals(emptyMenu));
	}
	
	@Test
	public void testUserFactoryCreateRestaurant() throws BadUserCreationException {
		UserFactory userFactory = new UserFactory();
		Restaurant restaurant2 = (Restaurant) userFactory.createUser("ReStAuRaNt", "PetitRestaurant", "petitresto", "1234", "1.9", "1.2");
		
		Assert.assertTrue(restaurant2.getName().equals("PetitRestaurant"));
		Assert.assertTrue(restaurant2.getUsername().equals("petitresto"));
		Assert.assertTrue(restaurant2.getPassword().equals("1234"));
		Assert.assertTrue(restaurant2.getLocation().equals(new Location(1.9, 1.2)));
	}
}