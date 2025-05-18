package food;

import java.util.Set;

public class MealFactory {

	@SuppressWarnings("unchecked") // The type o the Set (args[1]) is verified
	public Meal createMeal(String mealType, Object... args) throws BadMealTypeCreationException, BadNumberOfArgumentsException,
		BadArgumentTypeException, UnrecognizedDishException, BadMealFormulaException{

		// Verifying parameters number
		if (args.length != 4 | args.length != 5) {
			throw new BadNumberOfArgumentsException("Constructor must have 4 or 5 arguments.");
		}
		
		// Verifying parameters types
		if (!(args[0] instanceof String) | !(args[1] instanceof Set) | !(args[2] instanceof Boolean)
				| !(args[3] instanceof Boolean)) {
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
