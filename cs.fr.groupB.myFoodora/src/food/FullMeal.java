package food;

import java.util.Set;

public class FullMeal extends Meal{

	public FullMeal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian) {
		super(name, dishes, isGlutenFree, isVegetarian);
	}
	
	public FullMeal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian, PricingMealStrategy pricingStrategy) {
		super(name, dishes, isGlutenFree, isVegetarian, pricingStrategy);
	}

}
