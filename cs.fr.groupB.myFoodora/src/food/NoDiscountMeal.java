package food;

import java.util.Set;

public class NoDiscountMeal implements PricingMealStrategy {
	
	@Override
	public double getTotal(Set<Dish> dishes) {
		double total = 0;
		
		for (Dish dish : dishes) {
			total += dish.price;
		}
		
		return total;
	}
	
}
