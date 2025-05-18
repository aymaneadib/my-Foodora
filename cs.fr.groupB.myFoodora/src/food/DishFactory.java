package food;

public class DishFactory {
	
	public Dish createDish(String dishType, Object... args) throws BadNumberOfArgumentsException, BadMealTypeCreationException, BadArgumentTypeException {
		// Verifying parameters number
		if (args.length != 4) {
			throw new BadNumberOfArgumentsException("Constructor must have 4 or 5 arguments.");
		}
		
		// Verifying parameters types
		if (!(args[0] instanceof String) || !(args[1] instanceof Double) || !(args[2] instanceof Boolean) || !(args[3] instanceof Boolean)) {
			throw new BadArgumentTypeException("Unrecognized type of argument.");
		}
	
		return switch(dishType.toUpperCase()) {
				
		case "STARTER" -> new Starter((String) args[0], (Double) args[1], (Boolean) args[2], (Boolean) args[3]);
		
		case "MAINDISH" -> new MainDish((String) args[0], (Double) args[1], (Boolean) args[2], (Boolean) args[3]);
		
		case "DESSERT" -> new Dessert((String) args[0], (Double) args[1], (Boolean) args[2], (Boolean) args[3]);
		
		default -> throw new BadMealTypeCreationException("Unrecognized type of dish " + dishType + ".");
				
		};
	}

}
