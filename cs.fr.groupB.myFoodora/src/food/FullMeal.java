package food;

import java.util.Set;

public class FullMeal extends Meal{

	public FullMeal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian)
			throws UnrecognizedDishException, BadMealFormulaException{
	
		super(name, dishes, isGlutenFree, isVegetarian);
		
		// Verifying consistency of meal
		if(!isFormulaConsistent(dishes)) {
			throw new BadMealFormulaException("Bad combination of dishes for this meal.");
		}
	}
	
	public FullMeal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian, PricingMealStrategy pricingStrategy)
			throws UnrecognizedDishException, BadMealFormulaException{
		
		super(name, dishes, isGlutenFree, isVegetarian, pricingStrategy);
		
		// Verifying consistency of meal
		if(!isFormulaConsistent(dishes)) {
			throw new BadMealFormulaException("Bad combination of dishes for this meal.");
		}
	}
	
	// Verify if the Meal is composed by a starter, a MainDish and a Dessert
	private boolean isFormulaConsistent(Set<Dish> dishes) throws UnrecognizedDishException, BadMealFormulaException{
		boolean starter = false;
		boolean mainDish = false;
		boolean dessert = false;
		
		for (Dish dish : dishes) {
			if (dish instanceof Starter) {
				starter = true;
			}
			else if (dish instanceof MainDish) {
				mainDish = true;
			}
			else if (dish instanceof Dessert) {
				dessert = true;
			}
			else {
				throw new UnrecognizedDishException("Unrecognized type of dish.");
			}
		}
		
		if (!mainDish | dishes.size() != 3) {
			throw new BadMealFormulaException("This meal is not composed of 3 dishes.");
		}
		
		return (starter && mainDish && dessert);
	}

}
