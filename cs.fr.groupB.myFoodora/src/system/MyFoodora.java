package system;

import order.*;
import users.*;

import java.util.HashSet;
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
    private HashSet<Order> orderHistory;
    private ProfitData profitData;
    private DeliveryStrategy deliveryStrategy;
    private ProfitStrategy profitStrategy;
    private DishFactory dishFactory;
    private MealFactory mealFactory;
    private UserFactory userFactory;
    

    private MyFoodora() {
    	customers = new HashSet<Customer>();
    	restaurants = new HashSet<Restaurant>();
    	managers = new HashSet<Manager>();
    	couriers = new HashSet<Courier>();
    	orderHistory = new HashSet<Order>();
    	profitData = new ProfitData(0, 0, 0);
    	deliveryStrategy = new FairOccupationDelivery();
    	profitStrategy = new TargetProfitDeliveryCostOriented();
    	dishFactory = new DishFactory();
    	mealFactory = new MealFactory();
    	userFactory = new UserFactory();
    }
    
    public static MyFoodora getInstance() {
    	if (instance == null) {
    		instance = new MyFoodora();
    	}
    	
    	return instance;
    }

    public HashSet<Order> getOrderHistory() {
        return orderHistory;
    }
    
    
    
    
}
