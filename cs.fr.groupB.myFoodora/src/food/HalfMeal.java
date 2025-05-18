package food;

import java.util.Set;

public class HalfMeal extends Meal{
	
	private FormulaType formula;

	public HalfMeal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian)
			 throws BadMealFormulaException, UnrecognizedDishException {
		super(name, dishes, isGlutenFree, isVegetarian);
		this.formula = verifyFormulaType(dishes);
	}
	
	public HalfMeal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian,
			PricingMealStrategy pricingStrategy)  throws BadMealFormulaException, UnrecognizedDishException {
		super(name, dishes, isGlutenFree, isVegetarian, pricingStrategy);
		this.formula = verifyFormulaType(dishes);
	}
	
	public FormulaType getFormula() {
		return formula;
	}

	public void setFormula(FormulaType formula) {
		this.formula = formula;
	}

	private FormulaType verifyFormulaType(Set<Dish> dishes) throws BadMealFormulaException, UnrecognizedDishException {
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
				throw new UnrecognizedDishException("Unrecognized type of dish");
			}
		}
		
		if (!mainDish | dishes.size() != 2) {
			throw new BadMealFormulaException("Bad combination of dishes for this meal");
		}
		
		if (starter) {
			return FormulaType.MAINDISH_STARTER;
		}
		
		if (dessert) {
			return FormulaType.MAINDISH_DESSERT;
		}
		
		return null;
	}

}
