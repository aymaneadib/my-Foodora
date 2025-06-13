package test;

import java.util.ArrayList;
import java.util.Arrays;
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
import order.Order;
import user.BadUserCreationException;
import user.Courier;
import user.Customer;
import user.Location;
import user.Restaurant;
import user.UserFactory;

/**
 * Test class for Courier class.
 * 
 * @author Alisson Bonatto
 */
public class TestCourier {

	static Courier courier1;

	@BeforeClass
	public static void initializeTests() throws BadUserCreationException{
		courier1 = new Courier("Bernard", "Petit", "bernardpetit2025", "1234", "+testcourierphone", new Location(5, 9));	
	}
	
	@Test
	public void testCourierConstructor() {
		Assert.assertTrue(courier1.getName().equals("Bernard"));
		Assert.assertTrue(courier1.getSurname().equals("Petit"));
		Assert.assertTrue(courier1.getUsername().equals("bernardpetit2025"));
		Assert.assertTrue(courier1.getPassword().equals("1234"));
		Assert.assertTrue(courier1.getPhoneNumber().equals("+testcourierphone"));
		Assert.assertTrue(courier1.getPosition().equals(new Location(5, 9)));
		Assert.assertTrue(courier1.getDeliveryCounter() == 0);
		Assert.assertTrue(courier1.isOnDuty() == false);
		Assert.assertTrue(courier1.isActive() == true);
	}
	
	@Test
	public void testCourierSetters() throws BadUserCreationException {
		Courier courierTest = new Courier("foo", "foo", "foo", "foo", "foo", new Location(0, 0));
		courierTest.setName("Alisson");
		courierTest.setSurname("Bonatto");
		courierTest.setUsername("alissonbonatto2025");
		courierTest.setPassword("1234");
		courierTest.setPhoneNumber("+559999");
		courierTest.setPosition(new Location(1.1, 1.2));
		courierTest.setDeliveryCounter(5);
		courierTest.deactivateUser();
		courierTest.setOnDuty(true);
		
		Assert.assertTrue(courierTest.getName().equals("Alisson"));
		Assert.assertTrue(courierTest.getSurname().equals("Bonatto"));
		Assert.assertTrue(courierTest.getUsername().equals("alissonbonatto2025"));
		Assert.assertTrue(courierTest.getPassword().equals("1234"));
		Assert.assertTrue(courierTest.getPhoneNumber().equals("+559999"));
		Assert.assertTrue(courierTest.getPosition().equals(new Location(1.1, 1.2)));
		Assert.assertTrue(courierTest.getDeliveryCounter() == 5);
		Assert.assertTrue(courierTest.isActive() == false);
		Assert.assertTrue(courierTest.isOnDuty() == true);
	}
	
	@Test
	public void testDeliveryCounter() {
		courier1.setDeliveryCounter(3);
		courier1.incrementDeliveryCount();
		
		Assert.assertTrue(courier1.getDeliveryCounter() == 4);
		
		courier1.setDeliveryCounter(0);
		
		Assert.assertTrue(courier1.getDeliveryCounter() == 0);
	}
	
	@Test
	public void testToString() {
		String expectedString = courier1.getName() + " " + courier1.getSurname() + " ( " + courier1.getUsername() +
				" - Courrier with " + courier1.getDeliveryCounter() + " deliveries )";
		Assert.assertTrue(courier1.toString().equals(expectedString));
	}
	
	@Test
	public void testUserFactoryCreateCourier() throws BadUserCreationException {
		UserFactory userFactory = new UserFactory();
		Courier courierTest = (Courier) userFactory.createUser("courier", "name", "surname", "username", "password", "phone", "1", "50.1");
		
		Assert.assertTrue(courierTest.getName().equals("name"));
		Assert.assertTrue(courierTest.getSurname().equals("surname"));
		Assert.assertTrue(courierTest.getUsername().equals("username"));
		Assert.assertTrue(courierTest.getPassword().equals("password"));
		Assert.assertTrue(courierTest.getPhoneNumber().equals("phone"));
		Assert.assertTrue(courierTest.getPosition().equals(new Location(1, 50.1)));
		Assert.assertTrue(courierTest.isOnDuty() == false);
		Assert.assertTrue(courierTest.isActive() == true);
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testPhoneNumberAlreadyUsed() throws BadUserCreationException {
		String courier1PhoneNumber = "+testcourierphone";
		@SuppressWarnings("unused")
		Courier courierSamePhoneNumber = new Courier("", "", "courierSamePhoneNumber", "1234", courier1PhoneNumber, new Location(5, 9));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testPhoneNumberAlreadyUsedViaSetter() throws BadUserCreationException {
		String courier1PhoneNumber = "+testcourierphone";
		Courier courierSamePhoneNumber = new Courier("", "", "courierSamePhoneNumberViaSetter", "1234", "0", new Location(5, 9));
		courierSamePhoneNumber.setPhoneNumber(courier1PhoneNumber);
	}
	
}
