package food;

import java.util.Set;

/**
 * A pricing strategy that applies a general discount to the total price of a meal.
 * The discount is applied as a percentage of the sum of the individual dish prices.
 * 
 * This class implements the PricingMealStrategy interface and provides a way to calculate
 * the total price of a meal after applying a fixed discount rate.
 * 
 * @author Alisson Bonatto
 */
public class GeneralDiscountMeal implements PricingMealStrategy {

	private double discount;

	/**
	 * Constructs a GeneralDiscountMeal with a custom discount rate.
	 * 
	 * @param discount the discount rate to apply
	 */
	public GeneralDiscountMeal(double discount) {
		this.discount = discount;
	}

	/**
	 * Constructs a GeneralDiscountMeal with a default discount rate of 5%.
	 */
	public GeneralDiscountMeal() {
		this.discount = 0.05;
	}

	/**
	 * Returns the current discount rate.
	 * 
	 * @return the discount rate
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
	 * Calculates the total price after applying the discount.
	 * 
	 * @param dishes the set of dishes that compose the meal
	 * @return the total discounted price of the meal
	 */
	@Override
	public double getTotal(Set<Dish> dishes) {
		double total = 0;
		
		for (Dish dish : dishes) {
			total += dish.price;
		}
		
		return total * (1-this.discount);
	}
}
