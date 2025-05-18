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
	 *             - args[2]: Boolean indicating if the meal is gluten-free
	 *             - args[3]: Boolean indicating if the meal is vegetarian
	 *             - args[4] (optional): PricingMealStrategy custom pricing strategy
	 * @return a Meal instance matching the specified type and parameters
	 * @throws BadMealTypeCreationException if the mealType is unrecognized
	 * @throws BadNumberOfArgumentsException if the number of arguments is incorrect (not 4 or 5)
	 * @throws BadArgumentTypeException if argument types are incorrect or Set is not of Dish
	 * @throws UnrecognizedDishException if a Dish in the Set is unrecognized
	 * @throws BadMealFormulaException if the meal composition is invalid
	 */
	@SuppressWarnings("unchecked") // The type of the Set (args[1]) is verified manually
	public Meal createMeal(String mealType, Object... args) throws BadMealTypeCreationException, BadNumberOfArgumentsException,
		BadArgumentTypeException, UnrecognizedDishException, BadMealFormulaException{

		// Verifying parameters number
		if (args.length != 4 && args.length != 5) {
			throw new BadNumberOfArgumentsException("Constructor must have 4 or 5 arguments.");
		}
		
		// Verifying parameters types
		if (!(args[0] instanceof String) || !(args[1] instanceof Set) || !(args[2] instanceof Boolean)
				|| !(args[3] instanceof Boolean)) {
			throw new BadArgumentTypeException("Unrecognized type of argument.");
		}
		
		// Check if the set is indeed a set of Dishes
		Set<?> testSet = (Set<?>) args[1];
	    boolean allDishes = testSet.stream().allMatch(element -> element instanceof Dish);
	    if (!allDishes) {
	    	throw new BadArgumentTypeException("Second argument isn't a Set of Dishes (i.e. Set<Dish>).");
	    }
		
		if (args.length == 5) {
			if(!(args[4] instanceof PricingMealStrategy)) {
				throw new BadArgumentTypeException("Unrecognized type of argument.");
			}
		}
		
		// Returning meal based on mealType
		return switch(mealType.toUpperCase()) {
		
		case "FULLMEAL" -> {
			Meal meal;
			
			if(args.length == 4) {
				meal = new FullMeal((String) args[0], (Set<Dish>) args[1], (Boolean) args[2], (Boolean) args[3]);
			}
			else {
				meal = new FullMeal((String) args[0], (Set<Dish>) args[1], (Boolean) args[2], (Boolean) args[3], (PricingMealStrategy) args[4]);
			}
			
			yield meal;
		}
		
		case "HALFMEAL" -> {
			Meal meal;
			
			if(args.length == 4) {
				meal = new HalfMeal((String) args[0], (Set<Dish>) args[1], (Boolean) args[2], (Boolean) args[3]);
			}
			else {
				meal = new HalfMeal((String) args[0], (Set<Dish>) args[1], (Boolean) args[2], (Boolean) args[3], (PricingMealStrategy) args[4]);
			}

			yield meal;
		}
		
		default -> throw new BadMealTypeCreationException("Unrecognized type of meal " + mealType + ".");
		
		};
		
	}
	
}
