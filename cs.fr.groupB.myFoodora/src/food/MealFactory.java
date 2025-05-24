package food;

import java.util.Set;

/**
 * Factory class for creating Meal objects based on given parameters.
 * It supports creation of FullMeal and HalfMeal with or without custom pricing strategies.
 * 
 * @author Alisson Bonatto
 */
public class MealFactory {

	/**
	 * Creates a Meal object based on the specified type and parameters.
	 * 
	 * @param mealType the type of meal to create ("FULLMEAL", "HALFMEAL")
	 * @param args variable arguments expected:
	 *             - args[0]: String name of the meal
	 *             - args[1]: Set<Dish> dishes included in the meal
	 *             - args[2] (optional): PricingMealStrategy custom pricing strategy
	 * @return a Meal instance matching the specified type and parameters
	 * @throws BadMealTypeCreationException if the mealType is unrecognized
	 * @throws BadNumberOfArgumentsException if the number of arguments is incorrect (not 2 or 3)
	 * @throws BadArgumentTypeException if argument types are incorrect or Set is not of Dish
	 * @throws UnrecognizedDishException if a Dish in the Set is unrecognized
	 * @throws BadMealFormulaException if the meal composition is invalid
	 */
	@SuppressWarnings("unchecked") // The type of the Set (args[1]) is verified manually
	public Meal createMeal(String mealType, Object... args) throws BadMealTypeCreationException, BadNumberOfArgumentsException,
		BadArgumentTypeException, UnrecognizedDishException, BadMealFormulaException{

		// Verifying parameters number
		if (args.length != 2 && args.length != 3) {
			throw new BadNumberOfArgumentsException("Constructor must have 2 or 3 arguments.");
		}
		
		// Verifying parameters types
		if (!(args[0] instanceof String) || !(args[1] instanceof Set)) {
			throw new BadArgumentTypeException("Unrecognized type of argument.");
		}
		
		// Check if the set is indeed a set of Dishes
		Set<?> testSet = (Set<?>) args[1];
	    boolean allDishes = testSet.stream().allMatch(element -> element instanceof Dish);
	    if (!allDishes) {
	    	throw new BadArgumentTypeException("Second argument isn't a Set of Dishes (i.e. Set<Dish>).");
	    }
		
		if (args.length == 3) {
			if(!(args[2] instanceof PricingMealStrategy)) {
				throw new BadArgumentTypeException("Unrecognized type of argument.");
			}
		}
		
		// Returning meal based on mealType
		return switch(mealType.toUpperCase()) {
		
		case "FULLMEAL" -> {
			Meal meal;
			
			if(args.length == 2) {
				meal = new FullMeal((String) args[0], (Set<Dish>) args[1]);
			}
			else {
				meal = new FullMeal((String) args[0], (Set<Dish>) args[1], (PricingMealStrategy) args[2]);
			}
			
			yield meal;
		}
		
		case "HALFMEAL" -> {
			Meal meal;
			
			if(args.length == 2) {
				meal = new HalfMeal((String) args[0], (Set<Dish>) args[1]);
			}
			else {
				meal = new HalfMeal((String) args[0], (Set<Dish>) args[1], (PricingMealStrategy) args[2]);
			}

			yield meal;
		}
		
		default -> throw new BadMealTypeCreationException("Unrecognized type of meal " + mealType + ".");
		
		};
		
	}
	
}
