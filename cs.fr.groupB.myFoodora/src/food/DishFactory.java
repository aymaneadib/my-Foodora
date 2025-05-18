package food;

/**
 * Factory class to create Dish objects of various types.
 * This class provides a method to instantiate different subclasses of Dish
 * based on a given dish type and constructor arguments.
 * 
 * @author Alisson Bonatto
 */
public class DishFactory {

    /**
     * Creates a Dish object based on the provided dish type and constructor arguments.
     * 
     * @param dishType the type of the dish to create ("STARTER", "MAINDISH", "DESSERT")
     * @param args variable arguments required for the Dish constructor:
     *             - args[0]: String representing the name of the dish
     *             - args[1]: Double representing the price of the dish
     *             - args[2]: Boolean indicating if the dish is vegetarian
     *             - args[3]: Boolean indicating if the dish is gluten-free
     * @return a new instance of a subclass of Dish corresponding to the dishType
     * @throws BadNumberOfArgumentsException if the number of arguments is not exactly 4
     * @throws BadArgumentTypeException if any of the arguments are of incorrect type
     * @throws BadDishTypeCreationException if the dishType does not match any known type
     */
    public Dish createDish(String dishType, Object... args) throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
        // Verifying parameters number
        if (args.length != 4) {
            throw new BadNumberOfArgumentsException("Constructor must have 4 arguments.");
        }

        // Verifying parameters types
        if (!(args[0] instanceof String) || !(args[1] instanceof Double) || !(args[2] instanceof Boolean) || !(args[3] instanceof Boolean)) {
            throw new BadArgumentTypeException("Unrecognized type of argument.");
        }

        // Returns the dish based on dishType
        return switch(dishType.toUpperCase()) {
            case "STARTER" -> new Starter((String) args[0], (Double) args[1], (Boolean) args[2], (Boolean) args[3]);
            
            case "MAINDISH" -> new MainDish((String) args[0], (Double) args[1], (Boolean) args[2], (Boolean) args[3]);
            
            case "DESSERT" -> new Dessert((String) args[0], (Double) args[1], (Boolean) args[2], (Boolean) args[3]);
            
            default -> throw new BadDishTypeCreationException("Unrecognized type of dish " + dishType + ".");
        };
    }
}
