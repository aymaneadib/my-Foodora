package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import users.BadUserCreationException;
import users.Customer;
import users.Location;

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
	
	@Test(expected = BadUserCreationException.class)
	public void testUserAlreadyExists() throws BadUserCreationException {
		Customer customerLucasSameUserName = new Customer("", "", "lucaspetit", "", "", "", new Location(0, 0));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testEmailAlreadyUsed() throws BadUserCreationException {
		Customer customerTheoSameEmail = new Customer("", "", "", "", "", "theo.bernard@email.com", new Location(0, 0));
	}
	
	@Test(expected = BadUserCreationException.class)
	public void testPhoneNumberAlreadyUsed() throws BadUserCreationException {
		Customer customerTheoSameNumber = new Customer("", "", "", "", "+33666666666", "", new Location(0, 0));
	}


}
