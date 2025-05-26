package test;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fidelity.LotteryCard;
import food.BadMealFormulaException;
import food.Dessert;
import food.Dish;
import food.HalfMeal;
import food.MainDish;
import food.UnrecognizedDishException;
import users.BadUserCreationException;
import users.Customer;
import users.Location;
import users.UserFactory;

/**
 * Test class for all Costumer class.
 * 
 * @author Aymane Adib
 */
public class TestCustomer {
	
	static Customer customerLucas;
	static Customer customerTheo;
	
	@BeforeClass
	public static void initializeTests() throws BadUserCreationException {
		customerLucas = new Customer("Lucas", "Petit", "lucaspetit", "1234", "+33777777777", "lucas.petit@email.com", new Location(0, 0));
		customerTheo = new Customer("Theo", "Bernard", "theobernard", "abcd", "+33666666666", "theo.bernard@email.com", new Location(1, 0), true);
	}

	@Test
	public void testCustomerCreationNoConsentConstructor() {
		Assert.assertTrue(customerLucas.getName().equals("Lucas"));
		Assert.assertTrue(customerLucas.getSurname().equals("Petit"));
		Assert.assertTrue(customerLucas.getUsername().equals("lucaspetit"));
		Assert.assertTrue(customerLucas.getPassword().equals("1234"));
		Assert.assertTrue(customerLucas.getPhoneNumber().equals("+33777777777"));
		Assert.assertTrue(customerLucas.getEmail().equals("lucas.petit@email.com"));
		Assert.assertTrue(customerLucas.getAdress().equals(new Location(0, 0)));
		Assert.assertTrue(customerLucas.isNotificationsConsent() == false);
		Assert.assertTrue(customerLucas.isActive() == true);
	}
	
	@Test
	public void testCustomerCreationConsentConstructor() {
		Assert.assertTrue(customerTheo.getName().equals("Theo"));
		Assert.assertTrue(customerTheo.getSurname().equals("Bernard"));
		Assert.assertTrue(customerTheo.getUsername().equals("theobernard"));
		Assert.assertTrue(customerTheo.getPassword().equals("abcd"));
		Assert.assertTrue(customerTheo.getPhoneNumber().equals("+33666666666"));
		Assert.assertTrue(customerTheo.getEmail().equals("theo.bernard@email.com"));
		Assert.assertTrue(customerTheo.getAdress().equals(new Location(1, 0)));
		Assert.assertTrue(customerTheo.isNotificationsConsent() == true);
		Assert.assertTrue(customerTheo.isActive() == true);
	}
	
	@Test
	public void testSetters() throws BadUserCreationException {
		Customer customerEmpty = new Customer(".", ".", "", ".", ".", ".", null);
		customerEmpty.setName("Alisson");
		customerEmpty.setSurname("Bonatto");
		customerEmpty.setUsername("alissonbonatto");
		customerEmpty.setPassword("1234");
		customerEmpty.setPhoneNumber("+55999999999");
		customerEmpty.setEmail("email@email.com");
		customerEmpty.setAdress(new Location(5.5, 5.5));
		customerEmpty.setNotificationsConsent(true);
		customerEmpty.deactivateUser();
		LotteryCard lotteryCard = new LotteryCard(customerEmpty);
		customerEmpty.setFidelityCard(lotteryCard);
		
		
		Assert.assertTrue(customerEmpty.getName().equals("Alisson"));
		Assert.assertTrue(customerEmpty.getSurname().equals("Bonatto"));
		Assert.assertTrue(customerEmpty.getUsername().equals("alissonbonatto"));
		Assert.assertTrue(customerEmpty.getPassword().equals("1234"));
		Assert.assertTrue(customerEmpty.getPhoneNumber().equals("+55999999999"));
		Assert.assertTrue(customerEmpty.getEmail().equals("email@email.com"));
		Assert.assertTrue(customerEmpty.getAdress().equals(new Location(5.5, 5.5)));
		Assert.assertTrue(customerEmpty.isNotificationsConsent() == true);
		Assert.assertTrue(customerEmpty.isActive() == false);
		Assert.assertTrue(customerEmpty.getFidelityCard().equals(lotteryCard));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testUserAlreadyExists() throws BadUserCreationException {
		String lucasUsername = "lucaspetit";
		@SuppressWarnings("unused")
		Customer customerLucasSameUserName = new Customer("", "", lucasUsername, "", "+331", "user.already.exists@email.com", new Location(0, 0));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testUserAlreadyExistsViaSetter() throws BadUserCreationException {
		String lucasUsername = "lucaspetit";
		Customer customerLucasSameUserName = new Customer("", "", "test", "", "+331", "user.already.exists@email.com", new Location(0, 0));
		customerLucasSameUserName.setUsername(lucasUsername);
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testEmailAlreadyUsed() throws BadUserCreationException {
		String emailTheo = "theo.bernard@email.com";
		@SuppressWarnings("unused")
		Customer customerTheoSameEmail = new Customer("", "", "customerTheoSameEmail", "", "+332", emailTheo, new Location(0, 0));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testEmailAlreadyUsedViaSetter() throws BadUserCreationException {
		String emailTheo = "theo.bernard@email.com";
		Customer customerTheoSameEmail = new Customer("", "", "customerTheoSameEmailViaSetter", "", "+332", "test", new Location(0, 0));
		customerTheoSameEmail.setEmail(emailTheo);
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testPhoneNumberAlreadyUsed() throws BadUserCreationException {
		String theoPhoneNumber = "+33666666666";
		@SuppressWarnings("unused")
		Customer customerTheoSameNumber = new Customer("", "", "customerTheoSameNumber", "", theoPhoneNumber,
				"customer.theosamenumber@email.com", new Location(0, 0));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testPhoneNumberAlreadyUsedViaSetter() throws BadUserCreationException {
		String theoPhoneNumber = "+33666666666";
		Customer customerTheoSameNumber = new Customer("", "", "customerTheoSameNumberViaSetter", "", "0",
				"customer.theosamenumber@email.com", new Location(0, 0));
		customerTheoSameNumber.setPhoneNumber(theoPhoneNumber);
	}
	
	@Test
	public void testToString() {
		String expectedStringCustomerLucas = "Lucas Petit <lucas.petit@email.com> ( lucaspetit - Customer )";
		String expectedStringCustomerTheo = "Theo Bernard <theo.bernard@email.com> ( theobernard - Customer )";
		Assert.assertEquals(expectedStringCustomerLucas, customerLucas.toString());
		Assert.assertEquals(expectedStringCustomerTheo, customerTheo.toString());
	}
	
	@Test
	public void testNotifications() throws BadMealFormulaException, UnrecognizedDishException {
		HashSet<Dish> dishes = new HashSet<Dish>();
		dishes.add(new Dessert("Dish1", 0, true, true));
		dishes.add(new MainDish("Dish2", 0, true, true));
		HalfMeal testMeal = new HalfMeal("Meal1", dishes);
		
		HalfMeal.registerObserver(customerTheo);
		testMeal.makeMealOfTheWeek();
		
		Assert.assertTrue(!customerTheo.getNotifications().equals(""));
		
		customerTheo.clearNotifications();
		
		Assert.assertTrue(customerTheo.getNotifications().equals(""));
	}
	
	@Test
	public void testUserFactoryCreatingCustomer() throws BadUserCreationException {
		UserFactory userFactory = new UserFactory();
		
		Customer newCustomer = (Customer) userFactory.createUser("customer", "Alexis", "T.", "alexist", "1234", "+334", "at@email.com", "0.1", "0");
		
		Assert.assertTrue(newCustomer.getName().equals("Alexis"));
		Assert.assertTrue(newCustomer.getSurname().equals("T."));
		Assert.assertTrue(newCustomer.getUsername().equals("alexist"));
		Assert.assertTrue(newCustomer.getPassword().equals("1234"));
		Assert.assertTrue(newCustomer.getPhoneNumber().equals("+334"));
		Assert.assertTrue(newCustomer.getEmail().equals("at@email.com"));
		Assert.assertTrue(newCustomer.getAdress().equals(new Location(0.1, 0)));
		Assert.assertTrue(newCustomer.isNotificationsConsent() == false);
		Assert.assertTrue(newCustomer.isActive() == true);
	}

}
