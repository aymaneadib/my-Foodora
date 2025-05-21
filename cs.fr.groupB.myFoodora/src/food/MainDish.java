package food;

/**
 * Represents a main dish in the system.
 * A main dish is a type of dish, it extends Dish.
 * 
 * @author Alisson Bonatto
 */
public class MainDish extends Dish {
	
	/**
     * Constructor for Dessert.
     * Initializes the dessert with the provided details.  
     * @param name name of the dessert,
     * @param price price of the dessert, 
	 * @param isGlutenFree true if the meal is gluten free, false otherwise
	 * @param isVegetarian true if the meal is vegetarian, false otherwise
     */
	public MainDish(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
		super(name, price, isVegetarian, isGlutenFree);
	}

	/**
	 * Returns a string representation of the MainDish.
	 * 
	 * @return a string representation of the MainDish
	 */
	@Override
	public String toString() {
		return name + " (Main Dish at " + price + "€)" ;
	}

}
