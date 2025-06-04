package system;

import order.*;
import user.*;
import food.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Singleton class representing the MyFoodora system, managing users, orders,
 * profit strategies, and other key system functionalities.
 * Contains all users, orders and profit data.
 * 
 * @author Alisson Bonatto
 */
public class MyFoodora {

	static MyFoodora instance;                   // Instance of the system (singleton design pattern)
    private User currentUser;                    // Current user logged in. null if there's no user logged in
    private Set<Customer> customers;             // Set of all costumers
    private Set<Restaurant> restaurants;         // Set of all restaurants
    private Set<Manager> managers;               // Set of all manages
    private Set<Courier> couriers;               // Set of all couriers
    private Map<String, User> userMap;           // HashMap <username, user>
    private HashSet<Order> orderHistory;         // All orders made using the system
    private ProfitData profitData;               // Profit data (markup percentage, service fee and delivery cost)
    private DeliveryStrategy deliveryStrategy;   // Delivery police (least occupied or fastest delivery)
    private ProfitStrategy profitStrategy;       // Profit strategy (markup percentage, service fee and delivery cost oriented)
    private DishFactory dishFactory;             // Factory of dishes
    private MealFactory mealFactory;             // Factory of meals
    private UserFactory userFactory;             // Factory of users
    
    /**
     * Private constructor to prevent external instantiation.
     * It's used by a static method ensuring the singleton design pattern.
     * Initializes all sets, maps, factories, and strategies with default values.
     */
    private MyFoodora() {
    	this.customers = new HashSet<Customer>();
    	this.restaurants = new HashSet<Restaurant>();
    	this.managers = new HashSet<Manager>();
    	this.couriers = new HashSet<Courier>();
    	this.userMap = new HashMap<String, User>();
    	this.orderHistory = new HashSet<Order>();
    	this.profitData = new ProfitData(0, 0, 0);
    	this.deliveryStrategy = new FairOccupationDelivery();
    	this.profitStrategy = new TargetProfitDeliveryCostOriented();
    	this.dishFactory = new DishFactory();
    	this.mealFactory = new MealFactory();
    	this.userFactory = new UserFactory();
    }
    
    /**
     * Returns the single instance of MyFoodora (singleton).
     * 
     * @return the MyFoodora instance
     */
    public static MyFoodora getInstance() {
    	if (instance == null) {
    		instance = new MyFoodora();
    	}
    	
    	return instance;
    }
    
    /**
     * Returns the current delivery strategy used by the system.
     *
     * @return the DeliveryStrategy currently in use
     */
    public DeliveryStrategy getDeliveryStrategy() {
        return deliveryStrategy;
    }

    /**
     * Sets the delivery strategy to be used by the system.
     *
     * @param deliveryStrategy the DeliveryStrategy to apply
     */
    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    /**
     * Gets the current user logged into the system.
     *
     * @return user the user logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
	/**
	 * Gets the user factory of the system.
	 *
	 * @return userFactory the user factory of the system
	 */
    public UserFactory getUserFactory() {
    	return this.userFactory;
    }

    /**
	 * Gets the user map of the system.
	 *
	 * @return userMap the user map of the system
	 */
	public Map<String, User> getUserMap() {
        return userMap;
    }
	
	/**
	 * Sets the user map of the system.
	 *
	 * @param userMap the user map to set
	 */
	public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    /**
     * Returns the set of registered customers in the system.
     *
     * @return a set of Customer objects
     */
    public Set<Customer> getCustomers() {
        return customers;
    }

    /**
     * Sets the registered customers in the system.
     *
     * @param customers a set of Customer objects to be assigned
     */
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Returns the set of registered restaurants in the system.
     *
     * @return a set of Restaurant objects
     */
    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Returns a restaurant by its name.
     * If no restaurant is found with the given name, it returns null instead of throwing an exception.
     *
     * @param name the name of the restaurant to search for
     * @return the Restaurant object if found, or null if not found
     */
    public Restaurant getRestaurantByName(String name) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                return restaurant; // Restaurant found, no exception thrown
            }
        }
        return null; // If no restaurant found with the given name
    }

    /**
     * Sets the registered restaurants in the system.
     *
     * @param restaurants a set of Restaurant objects to be assigned
     */
    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Returns the set of registered managers in the system.
     *
     * @return a set of Manager objects
     */
    public Set<Manager> getManagers() {
        return managers;
    }

    /**
     * Sets the registered managers in the system.
     *
     * @param managers a set of Manager objects to be assigned
     */
    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }

    /**
     * Returns the set of registered couriers in the system.
     *
     * @return a set of Courier objects
     */
    public Set<Courier> getCouriers() {
        return couriers;
    }

    /**
     * Sets the registered couriers in the system.
     *
     * @param couriers a set of Courier objects to be assigned
     */
    public void setCouriers(Set<Courier> couriers) {
        this.couriers = couriers;
    }


	/**
     * Returns the history of all completed orders.
     * 
     * @return a set of orders
     */
    public HashSet<Order> getOrderHistory() {
        return this.orderHistory;
    }
    
    /**
     * Sets history of all completed orders.
     * 
     * @param a set of orders
     */
    public void setOrderHistory(HashSet<Order> orders) {
    	this.orderHistory = orders;
    }
    
    /**
     * Sets the current profit strategy used by the platform.
     * 
     * @param strategy the new profit strategy
     */
    public void setProfitStrategy(ProfitStrategy strategy) {
    	this.profitStrategy = strategy;
    }
    
    /**
     * Sets the service fee used in profit calculation.
     * 
     * @param serviceFee the new service fee
     */
    public void setServiceFee(double serviceFee) {
    	this.profitData.setServiceFee(serviceFee);
    }
    
    /**
     * Sets the markup percentage used in profit calculation.
     * 
     * @param markupPercentage the new markup percentage
     */
    public void setMarkupPercentage(double markupPercentage) {
    	this.profitData.setMarkupPercentage(markupPercentage);
    }
    
    /**
     * Sets the delivery cost (internally uses setServiceFee).
     * 
     * @param deliveryCost the new delivery cost
     */
    public void setDeliveryCost(double deliveryCost) {
    	this.profitData.setDeliveryCost(deliveryCost);
    }
    
    /**
     * Returns the current profit data.
     * 
     * @return the profit data object
     */
    public ProfitData getProfitData() {
    	return this.profitData;
    }
    
    /**
     * Adds a user to the system and categorizes them into the appropriate user set.
     * Also adds the user into the mapping user.
     * 
     * @param user the user to add
     */
    public void addUser(User user) {
    	// Puts user in the map
    	this.userMap.put(user.getUsername(), user);
    	
    	// Adds user in the correct HashSet
    	if (user instanceof Customer) {
    		this.customers.add((Customer) user);
    	}
    	else if(user instanceof Restaurant) {
    		this.restaurants.add((Restaurant) user);
    	}
    	else if(user instanceof Courier) {
    		this.couriers.add((Courier) user);
    	}
    	else if(user instanceof Manager) {
    		this.managers.add((Manager) user);
    	}
    }
    
    /**
     * Removes a user from the system.
     * 
     * @param user the user to remove
     * @throws UserNotFoundException if user does not exist
     */
    public void removeUser(User user) throws UserNotFoundException{
    	// Removes user from map
    	User removedUser = this.userMap.remove(user.getUsername());
    	
    	// Throws an error if user was not found
    	if (removedUser == null) {
    		throw new UserNotFoundException("User " + user.getUsername() + " not found.");
    	}
    	
    	User.removeUsernameFromUsernamesUsed(user.getUsername());
    	
    	// Removes user from HashSet
    	if (user instanceof Customer) {
    		this.customers.remove((Customer) user);
    		Customer.removeEmailFromEmailsUsed(((Customer) user).getEmail());
    		Customer.removePhoneFromPhonesUsed(((Customer) user).getPhoneNumber());
    	}
    	else if(user instanceof Restaurant) {
    		this.restaurants.remove((Restaurant) user);
    	}
    	else if(user instanceof Courier) {
    		this.couriers.remove((Courier) user);
    		Customer.removePhoneFromPhonesUsed(((Courier) user).getPhoneNumber());
    	}
    	else if(user instanceof Manager) {
    		this.managers.remove((Manager) user);
    		Customer.removePhoneFromPhonesUsed(((Courier) user).getPhoneNumber());
    	}
    }
    
    /**
     * Registers a user with full personal information.
     * 
     * @param userType the type of user
     * @param name user's first name
     * @param lastName user's last name
     * @param username unique username
     * @param adress address of the user
     * @param password user's password
     * @param phoneNumber user's phone number
     * @throws BadUserCreationException if user creation fails
     */
    public void registerUser(String userType, String name, String lastName, String username, String adress,
    		String password, String phoneNumber) throws BadUserCreationException {
    	
    	User user = this.userFactory.createUser(userType, name, lastName, username, password, phoneNumber, adress);
    	this.addUser(user);
    }
    
    /**
     * Registers a user (alternative method with fewer arguments).
     * 
     * @param userType the type of user
     * @param name user's name
     * @param username unique username
     * @param adress user's address
     * @param password user's password
     * @throws BadUserCreationException if user creation fails
     */
    public void registerUser(String userType, String name, String username, String adress,
    		String password) throws BadUserCreationException {
    	
    	User user = this.userFactory.createUser(userType, name, username, password, adress);
    	this.addUser(user);
    }
    
    /**
     * Authenticates a user.
     * The system saves the user logged in the system
     * if authentication does not fails.
     * 
     * @param username the username
     * @param password the password
     * @throws UserNotFoundException if the user is not found
     * @throws IncorrectCredentialsException if credentials are incorrect
     */
    public void login(String username, String password) throws UserNotFoundException, IncorrectCredentialsException{
    	// User to be returned
    	User user = this.userMap.get(username);
    	
    	// Throws an error if user was not found
    	if (user == null) {
    		throw new UserNotFoundException("User " + username + " not found.");
    	}
    	
    	// Checking user password
    	if (user.getPassword().equals(password)) {
    		this.currentUser = user;
    	}
    	else {
    		throw new IncorrectCredentialsException("Username and password do not match.");
    	}
    }
    
    /**
     * Logs out the currently logged-in user.
     */
    public void logout() {
    	this.currentUser = null;
    }
    
    /**
     * Creates a new dish using the dish factory.
     * 
     * @param dishType type of dish (Starter, MainDish, Dessert)
     * @param name name of the dish
     * @param price price of the dish
     * @param isVegetarian vegetarian flag
     * @param isGlutenFree gluten-free flag
     * @return the created Dish
     * @throws BadNumberOfArgumentsException if arguments are incorrect
     * @throws BadDishTypeCreationException if the type is unrecognized
     * @throws BadArgumentTypeException if argument types are invalid
     */
    public Dish createDish(String dishType, String name, double price, boolean isVegetarian, boolean isGlutenFree) 
    		throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
    	
    	return this.dishFactory.createDish(dishType, name, price, isVegetarian, isGlutenFree);
    }
    
    /**
     * Creates a meal with default pricing strategy using
     * the meal factory.
     * 
     * @param mealType the type of meal
     * @param name name of the meal
     * @param dishes set of dishes
     * @return the created Meal
     * @throws UnrecognizedDishException if dish is unrecognized
     * @throws BadMealFormulaException if meal formula is invalid
     * @throws BadMealTypeCreationException if type is unrecognized
     * @throws BadNumberOfArgumentsException if argument count is wrong
     * @throws BadArgumentTypeException if argument types are invalid
     */
    public Meal createMeal(String mealType, String name, Set<Dish> dishes)
    		throws UnrecognizedDishException, BadMealFormulaException, BadMealTypeCreationException, BadNumberOfArgumentsException, BadArgumentTypeException{
    	
    	return this.mealFactory.createMeal(mealType, name, dishes);
    }
    
    /**
     * Creates a meal with a custom pricing strategy using
     * the meal factory.
     * 
     * @param mealType the type of meal
     * @param name name of the meal
     * @param dishes set of dishes
     * @param isGlutenFree gluten-free flag
     * @param isVegetarian vegetarian flag
     * @param pricingStrategy pricing strategy to apply
     * @return the created Meal
     * @throws UnrecognizedDishException if dish is unrecognized
     * @throws BadMealFormulaException if meal formula is invalid
     * @throws BadMealTypeCreationException if type is unrecognized
     * @throws BadNumberOfArgumentsException if argument count is wrong
     * @throws BadArgumentTypeException if argument types are invalid
     */
    public Meal createMeal(String mealType, String name, Set<Dish> dishes, PricingMealStrategy pricingStrategy)
    		throws UnrecognizedDishException, BadMealFormulaException, BadMealTypeCreationException, BadNumberOfArgumentsException, BadArgumentTypeException{
    	
    	return this.mealFactory.createMeal(mealType, name, dishes, pricingStrategy);
    }
    
    /**
     * Selects a courier to deliver an order based on current delivery police.
     * 
     * @param restaurantToPickDeliver the restaurant
     * @param customerToDeliver the customer
     * @return the selected courier
     * @throws AvailableCourierNotFoundException if no courier is available
     */
    public ArrayList<Courier> selectCourier(Restaurant restaurantToPickDeliver, Customer customerToDeliver) throws AvailableCourierNotFoundException{
    	// Selecting courier based on current policy
    	ArrayList<Courier> selectedCouriers = this.deliveryStrategy.selectCourier(this.couriers, restaurantToPickDeliver, customerToDeliver);
    	
    	// If there's no available couriers, throws an error
    	if (selectedCouriers == null) {
    		throw new AvailableCourierNotFoundException("Available courier not found.");
    	}
    	
    	return selectedCouriers;
    }
    
    /**
     * Returns a sorted list of dishes based on their delivery frequency.
     * 
     * @param dishes set of dishes to sort
     * @return sorted list of dishes
     */
    public ArrayList<Dish> sortDishesDeliveryFrequency(Set<Dish> dishes){
    	ArrayList<Dish> arrayDishes = new ArrayList<Dish>(dishes);
    	DishComparator comparator = new DishComparator();
    	
    	Collections.sort(arrayDishes, comparator);
    	return arrayDishes;
    }
    
    /**
     * Returns a sorted list of meals based on their delivery frequency.
     * 
     * @param meals set of meals to sort
     * @return sorted list of meals
     */
    public ArrayList<Meal> sortMealsDeliveryFrequency(Set<Meal> meals){
    	ArrayList<Meal> arrayMeals = new ArrayList<Meal>(meals);
    	MealComparator comparator = new MealComparator();
    	
    	Collections.sort(arrayMeals, comparator);
    	return arrayMeals;
    }
    
    /**
     * Notifies the current user with the meal of the week, if they gave consent.
     * 
     * @param restaurant the restaurant sending the notification
     * @return string describing the meal or null if no consent or user
     */
    public String notifyUser(Restaurant restaurant) {
    	// Here notifyUser = return the toString of the meal of the week
    	// returns null if the user did not give consensus
    	
    	String output = "";
    	
    	// If there's a user logged in
    	if (this.currentUser != null) {
        	if (this.currentUser instanceof Customer) {
        		if (((Customer) this.currentUser).isNotificationsConsent()) {
        			for(Meal meal : restaurant.getMenu().getMeals()) {
                		if (meal.isMealOfTheWeek()) {
                			output += meal;
                		}
                	}
        		}
        		else {
        			return null;
        		}
        	}
        	else {
        		return null;
        	}
    	}
    	else {
    		return null;
    	}
    	
    	return output;
    }

    /**
     * Creates a new order for the specified restaurant and customer.
     * Initializes the order with no dishes or meals.
     * The order is stored as the current order of the customer.
     *
     * @param restaurant the restaurant fulfilling the order
     * @param customer the customer placing the order
     */
    public Order createOrder(Restaurant restaurant, Customer customer){
        Order newOrder = new Order(customer, restaurant, null);
        customer.setCurrentOrder(newOrder);
        return newOrder;
    }

    
    /**
     * Creates and processes a new order for the specified customer and restaurant, including the selected dishes and meals.
     * A courier is automatically assigned based on the current delivery strategy.
     * The order is only processed if the current user is an active customer and there is at least one dish or meal.
     *
     * @param customer the customer placing the order
     * @param restaurant the restaurant fulfilling the order
     * @param dishes the set of individual dishes included in the order
     * @param meals the set of meals included in the order
     * @throws AvailableCourierNotFoundException if no available courier can be assigned to the order
     */
    public Order makeOrder(Customer customer, Restaurant restaurant, HashSet<Dish> dishes, HashSet<Meal> meals) throws AvailableCourierNotFoundException {
    	if (this.currentUser instanceof Customer) {
    		
    		// If the currentUser and restaurant are active and there's at least one dish or meal
    		if (((Customer) this.currentUser).isActive() && !(dishes.isEmpty() && meals.isEmpty()) && restaurant.isActive()) {
    			
				// Creating a new Order
    			Order newOrder = new Order(customer, restaurant, null);
    			
    			// Adding meals and dishes
    			for (Dish dish : dishes) {
    				newOrder.addDish(dish);
    			}
    			for (Meal meal : meals) {
    				newOrder.addMeal(meal);
    			}
    			
    			// Choosing the possible couriers
    			ArrayList<Courier> possibleCouriers = this.selectCourier(restaurant, customer);
    			newOrder.setPossibleCouriers(possibleCouriers);
    			
    			// Notifies first courier
    			newOrder.notifyNextCourier();
    			
    			// Getting and setting the final price based on fidelity card
    			double newPrice = ((Customer) this.currentUser).getFidelityCard().getFinalPrice(newOrder);
    			newOrder.setPrice(newPrice);
    			
    			// Adding order to history
    			this.orderHistory.add(newOrder);
                return newOrder;
    		}
    	}
        return null; // If the current user is not a customer or the order cannot be processed
    }

    /**
     * Updates the platform's profit data to meet a target profit over the last month.
     * 
     * @param targetProfit the profit goal
     */
    public void updateProfitDataFromTargetProfit(double targetProfit) {
    	// Getting the date interval
    	// Last month = last 30 days
    	LocalDate currentDate = LocalDate.now();
    	LocalDate lastMonthDate = currentDate.minusMonths(1);
    	
    	
    	// Searching for last month orders
    	Set<Order> lastMonthOrders = new HashSet<Order>();
    	
    	for(Order order : this.orderHistory) {
    		if(order.getDate().isAfter(lastMonthDate) && order.getDate().isBefore(currentDate)) {
    			lastMonthOrders.add(order);
    		}
    	}
    	
    	// Updating profit strategy
    	this.profitData = this.profitStrategy.getProfitData(profitData, lastMonthOrders, targetProfit);
    }
}
