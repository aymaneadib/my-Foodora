package food;

import java.util.Set;

/**
 * Interface for pricing strategies applied to meals.
 * 
 * @author Alisson Bonatto
 */
public interface PricingMealStrategy {
	
	/**
	 * Calculates the total price for a given set of dishes based on the pricing strategy.
	 * 
	 * @param dishes the set of dishes composing the meal
	 * @return the total price calculated according to the strategy
	 */
	public double getTotal(Set<Dish> dishes);

}
