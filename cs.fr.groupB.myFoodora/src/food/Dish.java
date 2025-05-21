package food;

/**
 * Class representing a dish in the system.
 * 
 * @author Alisson Bonatto
 */
public abstract class Dish {

	protected String name;
	protected double price;
	protected boolean isVegetarian;
	protected boolean isGlutenFree;
	protected int frequencyDelivery;
	
	/**
     * Constructor for Dish.
     * Initializes the dessert with the provided details.
	 * @param name the name of the dish
	 * @param price the price of the dish
	 * @param isVegetarian true if the dish is vegetarian, false otherwise
	 * @param isGlutenFree true if the dish is gluten-free, false otherwise
	 */
	public Dish(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
		this.name = name;
		this.price = price;
		this.isVegetarian = isVegetarian;
		this.isGlutenFree = isGlutenFree;
		this.frequencyDelivery = 0;
	}
	
	/**
	 * Gets the name of the dish.
	 * 
	 * @return the dish name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the dish.
	 * 
	 * @param name the new name of the dish
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the price of the dish.
	 * 
	 * @return the dish price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the dish.
	 * 
	 * @param price the new price of the dish
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Checks if the dish is vegetarian.
	 * 
	 * @return true if vegetarian, false otherwise
	 */
	public boolean isVegetarian() {
		return isVegetarian;
	}

	/**
	 * Sets the vegetarian status of the dish.
	 * 
	 * @param isVegetarian true to set the dish as vegetarian, false otherwise
	 */
	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	/**
	 * Checks if the dish is gluten-free.
	 * 
	 * @return true if gluten-free, false otherwise
	 */
	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	/**
	 * Sets the gluten-free status of the dish.
	 * 
	 * @param isGlutenFree true to set the dish as gluten-free, false otherwise
	 */
	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	/**
	 * Gets the frequency of deliveries of this dish.
	 * 
	 * @return the number of times this dish has been delivered
	 */
	public int getFrequencyDelivery() {
		return frequencyDelivery;
	}

	/**
	 * Sets the frequency of deliveries of this dish.
	 * 
	 * @param frequencyDelivery the new delivery frequency to set
	 */
	public void setFrequencyDelivery(int frequencyDelivery) {
		this.frequencyDelivery = frequencyDelivery;
	}

	/**
	 * Increments the frequency of deliveries by 1.
	 */
	public void incrementFrequencyDelivery() {
		this.frequencyDelivery++;
	}

	/**
	 * Decrements the frequency of deliveries by 1.
	 * 
	 */
	public void decrementFrequencyDelivery() {
		this.frequencyDelivery--;
	}
	
	/**
	 * Compares this dish to another object for equality.
	 * Two dishes are considered equal if their names are equal.
	 * 
	 * @param other the object to compare with
	 * @return true if the other object is a Dish with the same name, false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Dish) {
			Dish otherDish = (Dish) other;
			result = this.name.equals(otherDish.getName());
		}
	    
	    return result;
	}
	
	/**
	 * Returns a hash code value for the dish based on its name.
	 * 
	 * @return the hash code of the dish's name
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}