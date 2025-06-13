package food;

import java.util.Set;

/**
 * Pricing strategy that applies no discount.
 * Calculates the total price by summing up the prices of all dishes in the meal.
 * 
 * @author Alisson Bonatto
 */
public class NoDiscountMeal implements PricingMealStrategy {
	
	/**
	 * Calculates the total price of the meal without any discount.
	 * 
	 * @param dishes the set of dishes composing the meal
	 * @return the total price with no discount applied
	 */
	@Override
	public double getTotal(Set<Dish> dishes) {
		double total = 0;
		
		for (Dish dish : dishes) {
			total += dish.price;
		}
		
		return total;
	}
	
}
