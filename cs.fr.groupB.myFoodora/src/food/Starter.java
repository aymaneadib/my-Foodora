package food;

/**
 * Represents a Starter in the system.
 * Starter is a specific type of Dish, it extends Dish.
 * 
 * @author Alisson Bonatto
 */
public class Starter extends Dish {

    /**
     * Constructs a Starter with the specified name, price, vegetarian and gluten-free status.
     * 
     * @param name the name of the starter dish
     * @param price the price of the starter dish
	 * @param isGlutenFree true if the meal is gluten free, false otherwise
	 * @param isVegetarian true if the meal is vegetarian, false otherwise
     */
    public Starter(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
        super(name, price, isVegetarian, isGlutenFree);
    }

}
