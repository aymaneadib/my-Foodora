package food;

public class MealFactory {

	public Meal createMeal(String mealType, Object... args) throws BadMealTypeCreationException, BadNumberOfArgumentsException, BadArgumentTypeException{

		// Verifying parameter types and number
		if (args.length != 4 | args.length != 5) {
			throw new BadNumberOfArgumentsException("Constructor must have 4 or 5 arguments.");
		}
		
		if () {
			throw new BadArgumentTypeException("Unrecognized type of argument.");
		}
		
		if (args.length == 5) {
			if() {
				throw new BadArgumentTypeException("Unrecognized type of argument.");
			}
		}
		
		// Returning meal based on mealType
		return switch(mealType.toUpperCase()) {
		
		case "FullMeal" -> {
			Meal meal;
			
			yield meal;
		}
		
		case "HalfMeal" -> {
			Meal meal;

			yield meal;
		}
		
		default -> throw new BadMealTypeCreationException("Unrecognized type of meal.");
		
		};
		
	}
	
}
