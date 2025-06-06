package food;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * Represents a full meal in the system.
 * A full meal is composed of exactly three dishes: a Starter, a MainDish, and a Dessert.
 * This class verifies that the meal formula is consistent.
 * 
 * @author Alisson Bonatto
 */
public class FullMeal extends Meal{

	/**
	 * Constructs a FullMeal with the specified name, set of dishes, and restrictions.
	 * 
	 * @param name the name of the meal
	 * @param dishes the dishes composing the meal; must contain exactly one Starter, one MainDish, and one Dessert
	 * @throws UnrecognizedDishException if any dish in the set is not recognized as Starter, MainDish or Dessert
	 * @throws BadMealFormulaException if the combination of dishes does not follow the full meal formula
	 */
	public FullMeal(String name, Set<Dish> dishes)
			throws UnrecognizedDishException, BadMealFormulaException{
	
		super(name, dishes);
		
		// Verifying consistency of meal
		if(!isFormulaConsistent(dishes)) {
			throw new BadMealFormulaException("Bad combination of dishes for this meal.");
		}
	}
	
	/**
	 * Constructs a FullMeal with a custom pricing strategy.
	 * 
	 * @param name the name of the meal
	 * @param dishes the dishes composing the meal; must contain exactly one Starter, one MainDish, and one Dessert
	 * @param pricingStrategy the pricing strategy applied to this meal
	 * @throws UnrecognizedDishException if any dish in the set is not recognized as Starter, MainDish or Dessert
	 * @throws BadMealFormulaException if the combination of dishes does not follow the full meal formula
	 */
	public FullMeal(String name, Set<Dish> dishes, PricingMealStrategy pricingStrategy)
			throws UnrecognizedDishException, BadMealFormulaException{
		
		super(name, dishes, pricingStrategy);
		
		// Verifying consistency of meal
		if(!isFormulaConsistent(dishes)) {
			throw new BadMealFormulaException("Bad combination of dishes for this meal.");
		}
	}
	
	/**
	 * Checks if the set of dishes matches the formula of a full meal (one Starter, one MainDish, one Dessert).
	 * 
	 * @param dishes the set of dishes to verify
	 * @return true if the dishes contain exactly one Starter, one MainDish, and one Dessert; false otherwise
	 * @throws UnrecognizedDishException if a dish is not Starter, MainDish or Dessert
	 * @throws BadMealFormulaException if the meal does not contain exactly 3 dishes or no MainDish
	 */
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
			throw new BadMealFormulaException("Incorrect formula of FullMeal.");
		}
		
		return (starter && mainDish && dessert);
	}
	
	@Override
	public String toString() {
		String returnString = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		
		returnString += "Full Meal " + name + " - " + df.format(this.getPrice()) + "€ - ";
		returnString += "Composed of:";
		for (Dish dish : this.dishes) {
			returnString += " " + dish.getName() + ",";
		}
		
		// Removing lasts ", "
		returnString = returnString.substring(0, returnString.length() - 1);
		returnString += ".";
		
		return returnString;
	}

}
