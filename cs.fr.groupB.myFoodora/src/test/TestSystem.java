package test;

import java.util.*;

import org.junit.*;

import food.*;
import order.*;
import system.*;
import users.*;

/**
 * Test class for the MyFoodora system.
 * This class contains tests to ensure the correct initialization and functionality of the MyFoodora system.
 * It uses JUnit for testing.
 * 
 * @author Aymane Adib
 */
public class TestSystem {

    private static MyFoodora system;
    private static User manager1,manager2, restaurant1, restaurant2, restaurant3, courier1, courier2, courier3, customer1, customer2;
    private static Dish dish1, dish2,dish3;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Initialize the system before any tests run
        system = MyFoodora.getInstance();
        UserFactory userFactory = new UserFactory();
        manager1 = userFactory.createUser("manager", "Paolo","Ballarini","man_ballarini","password123");
        manager2 = userFactory.createUser("manager", "Arnault","Lapitre","man_lapitre","password123");
        restaurant1 = userFactory.createUser("restaurant", "Pizzeria","pizzeria","password123","0.0","0.0");
        restaurant2 = userFactory.createUser("restaurant", "SushiPlace","sushi_place","password123","0.0","1.0");
        restaurant3 = userFactory.createUser("restaurant", "BurgerKing","burger_king","password123","0.0","-1.0");
        courier1 = userFactory.createUser("courier", "Aymane","Adib","cour_adib","password123","0123456789","0.0","0.0");
        courier2 = userFactory.createUser("courier", "Alisson","Bonnato","cour_bonnato","password123","0987654321","1.0","0.0");
        courier3 = userFactory.createUser("courier", "Luca","Bertini","cour_bertini","password123","1122334455","-1.0","0.0");
        customer1 = userFactory.createUser("customer", "Alice","Smith","cust_smith","password123","0666666666","alice.smith@centralesupelec.fr","-1.0","-1.0");
        customer2 = userFactory.createUser("customer", "Bob","Johnson","cust_johnson","password123","0777777777","bob.johnson@centralesupelec.fr","1.0","1.0");
        system.addUser(manager1);
        system.addUser(manager2);
        system.addUser(restaurant1);
        system.addUser(restaurant2);
        system.addUser(restaurant3);
        ((Courier) courier1).setOnDuty(true);
        system.addUser(courier1);
        ((Courier) courier2).setOnDuty(true);
        system.addUser(courier2);
        ((Courier) courier3).setOnDuty(true);
        system.addUser(courier3);
        system.addUser(customer1);
        system.addUser(customer2); 
        dish2 = system.createDish("MainDish", "Pizza Margherita", 12.50, true, true);
        dish3 = system.createDish("Dessert", "Tiramisu", 5.00, true, true);
        dish1 = system.createDish("Starter", "Bruschetta", 6.00, true, true);    
    }

    @Test
    public void testSystemInitialization() {
        // Test if the system initializes correctly
        Assert.assertNotNull("System should not be null after initialization", system);
    }

    @Test
    public void testUserCreation() {
        // Test if users are created correctly
        Assert.assertNotNull("Manager 1 should not be null", manager1);
        Assert.assertNotNull("Manager 2 should not be null", manager2);
        Assert.assertNotNull("Restaurant 1 should not be null", restaurant1);
        Assert.assertNotNull("Restaurant 2 should not be null", restaurant2);
        Assert.assertNotNull("Restaurant 3 should not be null", restaurant3);
        Assert.assertNotNull("Courier 1 should not be null", courier1);
        Assert.assertNotNull("Courier 2 should not be null", courier2);
        Assert.assertNotNull("Courier 3 should not be null", courier3);
        Assert.assertNotNull("Customer 1 should not be null", customer1);
        Assert.assertNotNull("Customer 2 should not be null", customer2);
    }

    @Test
    public void testCustomers() {
        // Test if users can log in correctly
        HashSet<User> expectedCustomers = new HashSet<User>();
        expectedCustomers.add(customer1);
        expectedCustomers.add(customer2);
        Assert.assertEquals(expectedCustomers, system.getCustomers());
    }

    @Test
    public void testRestaurants() {
        // Test if restaurants are correctly added to the system
        HashSet<User> expectedRestaurants = new HashSet<User>();
        expectedRestaurants.add(restaurant1);
        expectedRestaurants.add(restaurant2);
        expectedRestaurants.add(restaurant3);
        Assert.assertEquals(expectedRestaurants, system.getRestaurants());
    }

    @Test
    public void testCouriers() {
        // Test if couriers are correctly added to the system
        HashSet<User> expectedCouriers = new HashSet<User>();
        expectedCouriers.add(courier1);
        expectedCouriers.add(courier2);
        expectedCouriers.add(courier3);
        Assert.assertEquals(expectedCouriers, system.getCouriers());
    }

    @Test
    public void testManagers() {
        // Test if managers are correctly added to the system
        HashSet<User> expectedManagers = new HashSet<User>();
        expectedManagers.add(manager1);
        expectedManagers.add(manager2);
        Assert.assertEquals(expectedManagers, system.getManagers());
    }

    @Test
    public void testGetters() {
        // Test if getters return the correct values
        Assert.assertEquals("System should return the delivery strategy", new FairOccupationDelivery().getClass(), system.getDeliveryStrategy().getClass());
        Assert.assertEquals("System should return order history", new HashSet<Order>(), system.getOrderHistory());
    }

    @Test
    public void testRemoveUser() throws UserNotFoundException {
        // Test if a user can be removed from the system
        system.removeUser(customer2);
        Assert.assertFalse("Customer 1 should be removed from the system", system.getCustomers().contains(customer2));
    }

    @Test(expected = UserNotFoundException.class)
    public void testRemoveNonExistentUser() throws BadUserCreationException, UserNotFoundException {
        // Test if trying to remove a non-existent user throws an exception
        UserFactory userFactory =new UserFactory();
        User nonExistentUser = userFactory.createUser("customer", "User", "nO","cust_nonexistent", "password123", "0666696666", "nonexistant@centralesupelec.fr", "0.0", "0.0");
        system.removeUser(nonExistentUser);
    }

    @Test
    public void testLogin() throws BadUserCreationException, UserNotFoundException, IncorrectCredentialsException {
        // Test if a user can log in successfully
        system.login("cust_smith", "password123");
        Assert.assertEquals("Current user should be Customer 1", customer1, system.getCurrentUser());
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void testLoginWithIncorrectCredentials() throws BadUserCreationException, UserNotFoundException, IncorrectCredentialsException {
        // Test if logging in with incorrect credentials throws an exception
        system.login("cust_smith", "wrongpassword");
    }

    @Test(expected = UserNotFoundException.class)
    public void testLoginWithNonExistentUser() throws BadUserCreationException, UserNotFoundException, IncorrectCredentialsException {
        // Test if trying to log in with a non-existent user throws an exception
        system.login("cust_nonexistent", "password123");
    }

    @Test
    public void testLogout() throws BadUserCreationException, UserNotFoundException, IncorrectCredentialsException {
        // Test if a user can log out successfully
        system.login("cust_smith", "password123");
        system.logout();
        Assert.assertNull("Current user should be null after logout", system.getCurrentUser());
    }

    @Test
    public void testCreateDish() throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException{
        Dish dish1 = system.createDish("MainDish", "Pizza Margherita", 12.50, true, true);
        Dish dish2 = system.createDish("Dessert", "Tiramisu", 5.00, true, true);
        Assert.assertNotNull("Dish 1 should not be null", dish1);
        Assert.assertNotNull("Dish 2 should not be null", dish2);
    }

    @Test(expected = BadDishTypeCreationException.class)
    public void testCreateDishWithInvalidArguments() throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
        // Test if creating a dish with invalid arguments throws an exception
        system.createDish("NewType", "Pizza Margherita", 12.50, true, true); // Negative price
    }

    @Test
    public void testCreateMeal() throws  UnrecognizedDishException, BadMealFormulaException, BadMealTypeCreationException, BadNumberOfArgumentsException, BadArgumentTypeException{
        Set<Dish> fullMeal_dishes = new HashSet<Dish>(), halfMeal_dishes = new HashSet<Dish>();
        fullMeal_dishes.add(dish1);
        fullMeal_dishes.add(dish2);
        fullMeal_dishes.add(dish3);
        halfMeal_dishes.add(dish1);
        halfMeal_dishes.add(dish2);
        Meal fullMeal = system.createMeal("FullMeal", "Special 1", fullMeal_dishes);
        Meal halfMeal = system.createMeal("HalfMeal", "Special 2", halfMeal_dishes);
        Assert.assertNotNull("Full Meal should not be null", fullMeal);
        Assert.assertNotNull("Half Meal should not be null", halfMeal);
    }

    @Test
    public void testSelectCourier() throws AvailableCourierNotFoundException {
        // Test if a courier can be selected for an order
        Courier selectedCourier = system.selectCourier((Restaurant) restaurant1,(Customer) customer2);
        Assert.assertNotNull("Selected courier should not be null", selectedCourier);
    }
}
