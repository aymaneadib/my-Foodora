package food;

import java.util.List;
import java.util.Set;

/**
 * Abstract class representing a meal in the system.
 * A meal is composed of a set of dishes.
 * It maintains attributes such as gluten-free and vegetarian status,
 * delivery frequency, and pricing strategy.
 * 
 * @author Alisson Bonatto
 */
public abstract class Meal implements notification.Observable {

	private static List<notification.Observer> observers = new java.util.ArrayList<>();
    
	protected String name;
	protected Set<Dish> dishes;
	protected boolean isGlutenFree;
	protected boolean isVegetarian;
	protected int frequencyDelivery;
	protected PricingMealStrategy pricingMealStrategy;
	
	/**
	 * Constructs a Meal with the given name, dishes, gluten-free and vegetarian flags.
	 * Uses a default GeneralDiscountMeal pricing strategy.
	 * @param name the name of the meal
	 * @param dishes the set of dishes composing the meal
	 */
	public Meal(String name, Set<Dish> dishes) {
		this.name = name;
		this.dishes = dishes;
		this.isGlutenFree = verifyGlutenFree(dishes);
		this.isVegetarian = verifyVegetarian(dishes);
		this.frequencyDelivery = 0;
		this.pricingMealStrategy = new GeneralDiscountMeal();
	}
	
	/**
	 * Constructs a Meal with the given name, dishes, gluten-free and vegetarian flags,
	 * and a custom pricing strategy.
	 * 
	 * @param name the name of the meal
	 * @param dishes the set of dishes composing the meal
	 * @param pricingStrategy the pricing strategy to apply
	 */
	public Meal(String name, Set<Dish> dishes, PricingMealStrategy pricingStrategy) {
		this.name = name;
		this.dishes = dishes;
		this.isGlutenFree = verifyGlutenFree(dishes);
		this.isVegetarian = verifyVegetarian(dishes);
		this.frequencyDelivery = 0;
		this.pricingMealStrategy = pricingStrategy;
		if (pricingStrategy instanceof MealOfTheWeekDiscount) {
			notifyObservers();
		}
	}

	/**
	 * Returns the list of observers registered to this meal.
	 * 
	 * @return the list of observers
	 */
	public List<notification.Observer> getObservers() {
		return observers;
	}
	
	/**
	 * Returns the meal name.
	 * 
	 * @return the name of the meal
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the meal name.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the set of dishes composing the meal.
	 * 
	 * @return the set of dishes
	 */
	public Set<Dish> getDishes() {
		return dishes;
	}

	/**
	 * Sets the set of dishes composing the meal.
	 * 
	 * @param dishes the set of dishes to set
	 */
	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	/**
	 * Returns whether the meal is gluten-free.
	 * 
	 * @return true if all dishes are gluten-free, false otherwise
	 */
	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	/**
	 * Sets whether the meal is gluten-free.
	 * 
	 * @param isGlutenFree the gluten-free flag to set
	 */
	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	/**
	 * Returns whether the meal is vegetarian.
	 * 
	 * @return true if all dishes are vegetarian, false otherwise
	 */
	public boolean isVegetarian() {
		return isVegetarian;
	}

	/**
	 * Sets whether the meal is vegetarian.
	 * 
	 * @param isVegetarian the vegetarian flag to set
	 */
	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	/**
	 * Returns the delivery frequency of the meal.
	 * 
	 * @return the number of times the meal was delivered
	 */
	public int getFrequencyDelivery() {
		return frequencyDelivery;
	}

	/**
	 * Sets the delivery frequency of the meal.
	 * 
	 * @param frequencyDelivery the delivery frequency to set
	 */
	public void setFrequencyDelivery(int frequencyDelivery) {
		this.frequencyDelivery = frequencyDelivery;
	}

	/**
	 * Returns the pricing strategy used by the meal.
	 * 
	 * @return the pricing strategy
	 */
	public PricingMealStrategy getPricingStrategy() {
		return pricingMealStrategy;
	}

	/**
	 * Sets the pricing strategy for the meal.
	 * 
	 * @param pricingStrategy the pricing strategy to set
	 */
	public void setPricingStrategy(PricingMealStrategy pricingStrategy) {
		this.pricingMealStrategy = pricingStrategy;
		if (pricingStrategy instanceof MealOfTheWeekDiscount) {
			notifyObservers();
		}
	}

	/**
	 * Increments the delivery frequency counter by one.
	 */
	public void incrementFrequencyDelivery() {
		this.frequencyDelivery++;
	}

	/**
	 * Decrements the delivery frequency counter by one.
	 */
	public void decrementFrequencyDelivery() {
		this.frequencyDelivery--;
	}
	
	/**
	 * Calculates and returns the total price of the meal,
	 * according to the current pricing strategy.
	 * 
	 * @return the total price of the meal
	 */
	public double getPrice() {
		return this.pricingMealStrategy.getTotal(dishes);
	}

	/**
	 * Registers an observer to the meals of the week.
	 * 
	 * @param observer the observer to register
	 */
	public static void registerObserver(notification.Observer observer) {
		observers.add(observer);
	}

	/**
	 * Removes an observer from the meals of the week.
	 * 
	 * @param observer the observer to remove
	 */
	@Override
	public void removeObserver(notification.Observer observer) {
		observers.remove(observer);
	}

	/**
	 * Notifies all registered observers of the deal.
	 */
	@Override
	public void notifyObservers() {
		for (notification.Observer observer : observers) {
			observer.update(this);
		}
	}
	
	/**
	 * Checks if this meal currently has the "meal of the week" discount applied.
	 * 
	 * @return true if the pricing strategy is MealOfTheWeekDiscount, false otherwise
	 */
	public boolean isMealOfTheWeek() {
		return (this.pricingMealStrategy instanceof MealOfTheWeekDiscount);
	}
	
	/**
	 * Applies the "meal of the week" discount with a specified discount rate.
	 * 
	 * @param discount the discount rate to apply
	 */
	public void makeMealOfTheWeek(double discount) {
		this.pricingMealStrategy = new MealOfTheWeekDiscount(discount);
		notifyObservers();
	}
	
	/**
	 * Applies the default "meal of the week" discount.
	 */
	public void makeMealOfTheWeek() {
		this.pricingMealStrategy = new MealOfTheWeekDiscount();
		notifyObservers();
	}
	
	/**
	 * Removes the "meal of the week" discount by resetting to a general discount strategy.
	 */
	public void removeMealOfTheWeek() {
		this.pricingMealStrategy = new GeneralDiscountMeal();
	}
	
	/**
	 * Verifies if all dishes in the meal are gluten-free.
	 * 
	 * @param dishes the set of dishes to verify
	 * @return true if all dishes are gluten-free, false otherwise
	 */
	private boolean verifyGlutenFree(Set<Dish> dishes) {
		for(Dish dish : dishes) {
			if (!dish.isGlutenFree) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verifies if all dishes in the meal are vegetarian.
	 * 
	 * @param dishes the set of dishes to verify
	 * @return true if all dishes are vegetarian, false otherwise
	 */
	private boolean verifyVegetarian(Set<Dish> dishes) {
		for(Dish dish : dishes) {
			if (!dish.isVegetarian) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks equality between this meal and another object based on the meal name.
	 * 
	 * @param other the object to compare to
	 * @return true if the other object is a Meal with the same name, false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Meal) {
			Meal otherMeal = (Meal) other;
			result = (this.name == otherMeal.getName());
		}
	    return result;
	}
	
	/**
	 * Returns the hash code for this meal based on the name.
	 * 
	 * @return the hash code of the meal name
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
