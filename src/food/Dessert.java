package food;

import java.text.DecimalFormat;

/**
 * Class representing a dessert in the system.
 * A dessert is a type of dish, it extends Dish.
 * 
 * @author Alisson Bonatto
 */
public class Dessert extends Dish {
	
	/**
     * Constructor for Dessert.
     * Initializes the dessert with the provided details.  
     * @param name name of the dessert,
     * @param price price of the dessert, 
	 * @param isGlutenFree true if the meal is gluten free, false otherwise
	 * @param isVegetarian true if the meal is vegetarian, false otherwise
     */
	public Dessert(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
		super(name, price, isVegetarian, isGlutenFree);
	}

	/**
	 * Returns a string representation of the Dessert.
	 * 
	 * @return a string representation of the Dessert
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.00");
		return "Dessert " + name + " - " + df.format(price) + "€" ;
	}
}
