package system;

import order.*;
import users.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import food.*;
import order.*;
import users.*;

public class MyFoodora {

	static MyFoodora instance;
    private User currentUser;
    private Set<Customer> customers;
    private Set<Restaurant> restaurants;
    private Set<Manager> managers;
    private Set<Courier> couriers;
    private Map<String, User> userMap;
    private HashSet<Order> orderHistory;
    private ProfitData profitData;
    private DeliveryStrategy deliveryStrategy;
    private ProfitStrategy profitStrategy;
    private DishFactory dishFactory;
    private MealFactory mealFactory;
    private UserFactory userFactory;
    

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
    
    public static MyFoodora getInstance() {
    	if (instance == null) {
    		instance = new MyFoodora();
    	}
    	
    	return instance;
    }

    public HashSet<Order> getOrderHistory() {
        return this.orderHistory;
    }
    
    public void setProfitStrategy(ProfitStrategy strategy) {
    	this.profitStrategy = strategy;
    }
    
    public void setServiceFee(double serviceFee) {
    	this.profitData.setServiceFee(serviceFee);
    }
    
    public void setMarkupPercentage(double markupPercentage) {
    	this.profitData.setMarkupPercentage(markupPercentage);
    }
    
    public void setDeliveryCost(double deliveryCost) {
    	this.profitData.setServiceFee(deliveryCost);
    }
    
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
    
    public void removeUser(User user) throws UserNotFoundException{
    	// Removes user from map
    	User removedUser = this.userMap.remove(user.getUsername());
    	
    	// Throws an error if user was not found
    	if (removedUser == null) {
    		throw new UserNotFoundException("User " + user.getUsername() + " not found.");
    	}
    	
    	// Removes user from HashSet
    	if (user instanceof Customer) {
    		this.customers.remove((Customer) user);
    	}
    	else if(user instanceof Restaurant) {
    		this.restaurants.remove((Restaurant) user);
    	}
    	else if(user instanceof Courier) {
    		this.couriers.remove((Courier) user);
    	}
    	else if(user instanceof Manager) {
    		this.managers.remove((Manager) user);
    	}
    }
    
    //public void registerUser(String userType, String name, String lastName, String username, String adress, String password) {
    //	User user = this.userFactory(userType, )
    //}
    
    //public void registerUser(String userType, String name, String username, String adress, String password) {
    //	
    //}
    
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
    
    public void logout() {
    	this.currentUser = null;
    }
    
    public Courier selectCourier(Restaurant restaurantToPickDeliver, Customer customerToDeliver) throws AvailableCourierNotFoundException{
    	// Selecting courier based on current policy
    	Courier selectedCourier = this.deliveryStrategy.selectCourier(this.couriers, restaurantToPickDeliver, customerToDeliver);
    	
    	// If there's no available couriers, throws an error
    	if (selectedCourier == null) {
    		throw new AvailableCourierNotFoundException("Available courier not found.");
    	}
    	
    	return selectedCourier;
    }
    
    public double computeTotalIncome() {
    	double totalIncome = 0;
    	
    	// Computing total income (sum of price of all orders)
    	for (Order order : this.orderHistory) {
    		totalIncome += order.getPrice();
    	}
    	
    	return totalIncome;
    }
    
    public double computeTotalProfit() {
    	double totalProfit = 0;
    	
    	// Computing total profit
    	// profitForOneOrder = orderPrice*markupPercentage + serviceFee − deliveryCost
    	for (Order order : this.orderHistory) {
    		totalProfit += order.getPrice()*this.profitData.getMarkupPercentage() + this.profitData.getServiceFee() - this.profitData.getDeliveryCost();
    	}
    	
    	return totalProfit;
    }
    
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
