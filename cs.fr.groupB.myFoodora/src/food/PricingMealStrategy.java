package food;

import java.util.Set;

public interface PricingMealStrategy {
	
	public double getTotal(Set<Dish> dishes);

}
