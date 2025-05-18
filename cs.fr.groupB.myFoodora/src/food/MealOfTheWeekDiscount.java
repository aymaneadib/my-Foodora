package food;

import java.util.Set;

/**
 * Pricing strategy implementing a discount for the "Meal of the Week".
 * Applies a fixed percentage discount on the total price of the dishes.
 * 
 * @author Alisson Bonatto
 */
public class MealOfTheWeekDiscount implements PricingMealStrategy{

	private double discount;

	/**
	 * Constructs a MealOfTheWeekDiscount with a custom discount rate.
	 * 
	 * @param discount the discount rate to apply
	 */
	public MealOfTheWeekDiscount(double discount) {
		this.discount = discount;
	}
	
	/**
	 * Constructs a MealOfTheWeekDiscount with the default discount of 10%.
	 */
	public MealOfTheWeekDiscount() {
		this.discount = 0.1;
	}

	/**
	 * Gets the current discount rate.
	 * 
	 * @return the discount rate as a decimal (e.g., 0.1 for 10%)
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * Sets a new discount rate.
	 * 
	 * @param discount the new discount rate to apply
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Calculates the total discounted price for a given set of dishes.
	 * 
	 * @param dishes the set of dishes to price
	 * @return the total price after applying the discount
	 */
	@Override
	public double getTotal(Set<Dish> dishes) {
		double total = 0;
		
		for (Dish dish : dishes) {
			total += dish.price;
		}
		
		return total * this.discount;
	}
	
}
