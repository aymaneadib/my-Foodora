package cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import food.BadArgumentTypeException;
import food.BadDishTypeCreationException;
import food.BadMealFormulaException;
import food.BadMealTypeCreationException;
import food.BadNumberOfArgumentsException;
import food.Dessert;
import food.Dish;
import food.DishFactory;
import food.MainDish;
import food.Meal;
import food.MealFactory;
import food.MealOfTheWeekDiscount;
import food.Menu;
import food.Starter;
import food.UnrecognizedDishException;
import user.BadUserCreationException;
import user.Courier;
import user.Customer;
import user.Manager;
import user.Restaurant;
import user.User;
import user.UserFactory;

public class BasicScenarioGenerator{

	private Set<Customer> customers;             // Set of all costumers
    private Set<Restaurant> restaurants;         // Set of all restaurants
    private Set<Manager> managers;               // Set of all manages
    private Set<Courier> couriers;               // Set of all couriers
    private Map<String, User> userMap;           // HashMap <username, user>
    
    
	public BasicScenarioGenerator() {
		this.customers = new HashSet<Customer>();
		this.restaurants = new HashSet<Restaurant>();
		this.managers = new HashSet<Manager>();
		this.couriers = new HashSet<Courier>();
		this.userMap = new HashMap<String, User>();
	}
	
	public void createRandomUsers(int restaurantQuantity, int customerQuantity, int courierQuantity) throws BadUserCreationException,
				BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException, BadMealTypeCreationException,
				UnrecognizedDishException, BadMealFormulaException {
		this.restaurants = this.createRandomRestaurants(restaurantQuantity);
		this.customers = this.createRandomCustomers(customerQuantity);
		this.couriers = this.createRandomCouriers(courierQuantity);
		
		// Creating a single manager
		UserFactory userFactory = new UserFactory();
		Manager manager = (Manager) userFactory.createUser("manager", "ManagerName", "ManagerSurname", "admin", "admin");
		this.userMap.put(manager.getUsername(), manager);
		this.managers.add(manager);
	}
	
	public Set<Customer> getCreatedCustomers(){
		return this.customers;
	}
	
	public Set<Restaurant> getCreatedRestaurants(){
		return this.restaurants;
	}
	
	public Set<Courier> getCreatedCouriers(){
		return this.couriers;
	}
	
	public Map<String, User> getCreatedUserMap(){
		return this.userMap;
	}

	private HashSet<Customer> createRandomCustomers(int quantity) throws BadUserCreationException {
		UserFactory userFactory = new UserFactory();
		HashSet<Customer> customers = new HashSet<Customer>();
		Random random = new Random();
		
		for (int i = 0; i < quantity; i++) {
			String name = "customerName" + i;
			String surname = "customerSurname" + i;
			String username = "customerUsername" + i;
			String password = username;
			String phoneNumber = "+331" + i;
			String email = username + "@email.com";
			int randomX = random.nextInt(100 - (-100) + 1) - 100;
			int randomY = random.nextInt(100 - (-100) + 1) - 100;
			
			Customer customer = (Customer) userFactory.createUser("customer", name, surname, username, password,
					phoneNumber, email, String.valueOf(randomX), String.valueOf(randomY));
			customers.add(customer);
			this.userMap.put(customer.getUsername(), customer);
		}
		
		return customers;
	}
	
	private HashSet<Courier> createRandomCouriers(int quantity) throws BadUserCreationException{
		UserFactory userFactory = new UserFactory();
		HashSet<Courier> couriers = new HashSet<Courier>();
		Random random = new Random();
		int minCoordinate = -100;
		int maxCoordinate = 100;
		
		for (int i = 0; i < quantity; i++) {
			String name = "courierName" + i;
			String surname = "courierSurname" + i;
			String username = "courierUsername" + i;
			String password = username;
			String phoneNumber = "+332" + i;
			double randomX = random.nextDouble(maxCoordinate - minCoordinate + 1) + minCoordinate;
			double randomY = random.nextDouble(maxCoordinate - minCoordinate + 1) + minCoordinate;
			
			Courier courier = (Courier) userFactory.createUser("courier", name, surname, username, password, phoneNumber,
					String.valueOf(randomX), String.valueOf(randomY));
			couriers.add(courier);
			this.userMap.put(courier.getUsername(), courier);
		}
		
		return couriers;
	}
	
	private HashSet<Restaurant> createRandomRestaurants(int quantity) throws BadUserCreationException, BadNumberOfArgumentsException,
				BadDishTypeCreationException, BadArgumentTypeException, BadMealTypeCreationException, UnrecognizedDishException, BadMealFormulaException{
		UserFactory userFactory = new UserFactory();
		HashSet<Restaurant> restaurants = new HashSet<Restaurant>();
		Random random = new Random();
		int minCoordinate = -100;
		int maxCoordinate = 100;
		int minMeals = 3;
		int maxMeals = 10;
		
		for (int i = 0; i < quantity; i++) {
			// Creating restaurant
			String name = "restaurantName" + i;
			String username = "restaurantUsername" + i;
			String password = username;
			double randomX = random.nextDouble(maxCoordinate - minCoordinate + 1) + minCoordinate;
			double randomY = random.nextDouble(maxCoordinate - minCoordinate + 1) + minCoordinate;
			
			Restaurant restaurant = (Restaurant) userFactory.createUser("restaurant", name, username, password,
					String.valueOf(randomX), String.valueOf(randomY));
			
			// Creating menu
			int quantityMeals = random.nextInt(maxMeals - minMeals + 1) + minMeals;
			Menu menu = createRandomMenu(quantityMeals, name);
			restaurant.setMenu(menu);
			restaurants.add(restaurant);
			this.userMap.put(restaurant.getUsername(), restaurant);
		}
		
		return restaurants;
		
	}
	
	private Menu createRandomMenu(int quantityMeals, String restaurantName) throws BadNumberOfArgumentsException, BadDishTypeCreationException,
				BadArgumentTypeException, BadMealTypeCreationException, UnrecognizedDishException, BadMealFormulaException{
		// Useful variables
		ArrayList<Meal> meals = new ArrayList<Meal>();
		MealFactory mealFactory = new MealFactory();
		Random random = new Random();
		String[] mealTypes = {"FULLMEAL", "HALFMEAL1", "HALFMEAL2"};
		
		// Getting dishes
		HashSet<Dish> dishes = this.createRandomDishes(quantityMeals*5, restaurantName);
		ArrayList<Starter> starters = new ArrayList<Starter>();
		ArrayList<MainDish> mainDishes = new ArrayList<MainDish>();
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		
		for (Dish dish : dishes) {
			if (dish instanceof Starter) starters.add((Starter) dish);
			if (dish instanceof MainDish) mainDishes.add((MainDish) dish);
			if (dish instanceof Dessert) desserts.add((Dessert) dish);
		}
		
		// Creating meals
		for (int i = 0; i < quantityMeals; i++) {
			String chosenMealType = mealTypes[random.nextInt(mealTypes.length)];
			HashSet<Dish> chosenDishes = new HashSet<Dish>();
			Starter starter = starters.get(random.nextInt(starters.size()));
			MainDish mainDish = mainDishes.get(random.nextInt(mainDishes.size()));
			Dessert dessert = desserts.get(random.nextInt(desserts.size()));
			String name = "meal" + i + restaurantName;
			double currentProbability = random.nextDouble();
			
			Meal meal;
			if (chosenMealType.equals("FULLMEAL")) {
				chosenDishes.add(starter);
				chosenDishes.add(mainDish);
				chosenDishes.add(dessert);
				meal = mealFactory.createMeal("FULLMEAL", name, chosenDishes);
			} else if (chosenMealType.equals("HALFMEAL1")){
				chosenDishes.add(mainDish);
				chosenDishes.add(dessert);	
				meal = mealFactory.createMeal("HALFMEAL", name, chosenDishes);
			} else {
				chosenDishes.add(starter);
				chosenDishes.add(mainDish);
				meal = mealFactory.createMeal("HALFMEAL", name, chosenDishes);
			}
			
			// 5% probability of creating a meal of the week
			if (currentProbability > 0.95) {
				meal.setPricingStrategy(new MealOfTheWeekDiscount());
			}
			
			meals.add(meal);
		}
		
		// Creating at least one meal of the week
		meals.get(0).setPricingStrategy(new MealOfTheWeekDiscount());
		
		// Creating Menu
		Menu menu = new Menu(new HashSet<Starter>(starters), new HashSet<MainDish>(mainDishes),
				new HashSet<Dessert>(desserts), new HashSet<Meal>(meals), 0.05, 0.1);
		
		// Returning meals
		return menu;
	}
	
	private HashSet<Dish> createRandomDishes(int quantity, String restaurantName) throws BadNumberOfArgumentsException,
				BadDishTypeCreationException, BadArgumentTypeException{
		DishFactory dishFactory = new DishFactory();
		HashSet<Dish> dishes = new HashSet<Dish>();
		Random random = new Random();
		String[] dishTypes = {"STARTER", "MAINDISH", "DESSERT"};
		int minPrice = 10;
		int maxPrice = 300;
		
		for (int i = 0; i < quantity; i++) {
			String chosenDishType = dishTypes[random.nextInt(dishTypes.length)];
			String name = "dishName" + i + restaurantName;
			double randomPrice = random.nextDouble(maxPrice - minPrice + 1) + minPrice;

			Dish dish = (Dish) dishFactory.createDish(chosenDishType, name, randomPrice, random.nextBoolean(), random.nextBoolean());
			dishes.add(dish);
		}
		
		// Creating at least one dish of each type
		for (String dishType : dishTypes) {
			String name = "standard" + dishType;
			double randomPrice = random.nextDouble(maxPrice - minPrice + 1) + minPrice;
			
			Dish dish = (Dish) dishFactory.createDish(dishType, name, randomPrice, random.nextBoolean(), random.nextBoolean());
			dishes.add(dish);
		}
		return dishes;
	}
	
}
