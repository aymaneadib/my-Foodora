package food;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Represents a half meal in the system.
 * A half meal is a type of Meal composed of exactly two dishes.
 * The meal must contain a MainDish and either a Starter or a Dessert.
 * The formula type is determined based on the combination of dishes included.
 * 
 * @author Alisson Bonatto
 */
public class HalfMeal extends Meal{
	
	private FormulaType formula;

	/**
	 * Constructs a HalfMeal with specified parameters.
	 * 
	 * @param name the name of the meal
	 * @param dishes the dishes included in the meal
	 * @param isVegetarian true if the dish is vegetarian, false otherwise
	 * @param isGlutenFree true if the dish is gluten-free, false otherwise
	 * @throws BadMealFormulaException if the combination of dishes is invalid
	 * @throws UnrecognizedDishException if a dish type is unrecognized
	 */
	public HalfMeal(String name, Set<Dish> dishes) throws BadMealFormulaException, UnrecognizedDishException {
		super(name, dishes);
		this.formula = verifyFormulaType(dishes);
	}
	
	/**
	 * Constructs a HalfMeal with specified parameters and a pricing strategy.
	 * 
	 * @param name the name of the meal
	 * @param dishes the set of dishes included in the meal
	 * @param isVegetarian true if the dish is vegetarian, false otherwise
	 * @param isGlutenFree true if the dish is gluten-free, false otherwise
	 * @param pricingStrategy the pricing strategy to apply to the meal
	 * @throws BadMealFormulaException if the combination of dishes is invalid
	 * @throws UnrecognizedDishException if a dish type is unrecognized
	 */
	public HalfMeal(String name, Set<Dish> dishes, PricingMealStrategy pricingStrategy)  throws BadMealFormulaException, UnrecognizedDishException {
		super(name, dishes, pricingStrategy);
		this.formula = verifyFormulaType(dishes);
	}
	
	/**
	 * Returns the formula type of this half meal.
	 * 
	 * @return the formula type
	 */
	public FormulaType getFormula() {
		return formula;
	}

	/**
	 * Sets the formula type of this half meal.
	 * 
	 * @param formula the formula type to set
	 */
	public void setFormula(FormulaType formula) {
		this.formula = formula;
	}

	/**
	 * Verifies the formula type based on the dishes included in the meal.
	 * The meal must have exactly two dishes: a MainDish plus either a Starter or a Dessert.
	 * 
	 * @param dishes the set of dishes to verify
	 * @return the formula type corresponding to the dishes combination
	 * @throws BadMealFormulaException if the meal composition is invalid
	 * @throws UnrecognizedDishException if any dish type is not recognized
	 */
	private FormulaType verifyFormulaType(Set<Dish> dishes) throws BadMealFormulaException, UnrecognizedDishException {
		boolean starter = false;
		boolean mainDish = false;
		boolean dessert = false;
		
		// Check instance of dishes
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
				throw new UnrecognizedDishException("Unrecognized type of dish");
			}
		}
		
		// Check if it contains a main dish plus another dish
		if (!mainDish | dishes.size() != 2) {
			throw new BadMealFormulaException("Bad combination of dishes for this meal");
		}
		
		// Returns the type of the formula
		if (starter) {
			return FormulaType.MAINDISH_STARTER;
		}
		
		if (dessert) {
			return FormulaType.MAINDISH_DESSERT;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String returnString = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		
		returnString += "Half Meal " + name + " - " + df.format(this.getPrice()) + "€ - ";
		returnString += "Composed of:";
		for (Dish dish : this.dishes) {
			returnString += " " + dish.getName() + ", ";
		}
		
		// Removing lasts ", "
		returnString = returnString.substring(0, returnString.length() - 2);
		returnString += ".";
		
		return returnString;
	}

}
