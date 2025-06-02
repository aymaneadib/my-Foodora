package food;

import java.util.HashSet;
import java.util.Set;

public class Menu {
	
	private Set<Starter> starters;
	private Set<MainDish> mainDishes;
	private Set<Dessert> desserts;
	private Set<Meal> meals;
	private double generalDiscount;
	private double mealOfTheWeekDiscount;
	
	/**
	 * Creates a menu with specified sets of starters, main dishes, desserts, and meals,
	 * along with specified general and special discounts.
	 *
	 * @param starters the set of starters to include in the menu
	 * @param mainDishes the set of main dishes to include in the menu
	 * @param desserts the set of desserts to include in the menu
	 * @param meals the set of meals to include in the menu
	 * @param genericDiscout the general discount rate to apply
	 * @param mealOfTheWeekDiscount the special discount rate for the meal of the week
	 */
	public Menu(Set<Starter> starters, Set<MainDish> mainDishes, Set<Dessert> desserts, Set<Meal> meals,
	        double genericDiscout, double mealOfTheWeekDiscount) {
		if (genericDiscout < 0 || genericDiscout > 1 || mealOfTheWeekDiscount < 0 || mealOfTheWeekDiscount > 1) {
			throw new IllegalArgumentException("Discount must be between 0 and 1");
		}
	    this.starters = starters;
	    this.mainDishes = mainDishes;
	    this.desserts = desserts;
	    this.meals = meals;
	    this.generalDiscount = genericDiscout;
	    this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
	}

	/**
	 * Creates an empty menu (no dishes or meals) with specified general and special discounts.
	 *
	 * @param genericDiscout the general discount rate to apply
	 * @param mealOfTheWeekDiscount the special discount rate for the meal of the week
	 */
	public Menu(double genericDiscout, double mealOfTheWeekDiscount) {
		if (genericDiscout < 0 || genericDiscout > 1 || mealOfTheWeekDiscount < 0 || mealOfTheWeekDiscount > 1) {
			throw new IllegalArgumentException("Discount must be between 0 and 1");
		}
	    this.starters = new HashSet<Starter>();
	    this.mainDishes = new HashSet<MainDish>();
	    this.desserts = new HashSet<Dessert>();
	    this.meals = new HashSet<Meal>();
	    this.generalDiscount = genericDiscout;
	    this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
	}

	/**
	 * Creates an empty menu with no discounts.
	 */
	public Menu() {
	    this.starters = new HashSet<Starter>();
	    this.mainDishes = new HashSet<MainDish>();
	    this.desserts = new HashSet<Dessert>();
	    this.meals = new HashSet<Meal>();
	    this.generalDiscount = 0;
	    this.mealOfTheWeekDiscount = 0;
	}


	/** 
	 * @return the set of starters in the menu
	 */
	public Set<Starter> getStarters() {
		return starters;
	}

	/** 
	 * Sets the set of starters in the menu
	 * @param starters the new set of starters
	 */
	public void setStarters(Set<Starter> starters) {
		this.starters = starters;
	}

	/** 
	 * @return the set of main dishes in the menu
	 */
	public Set<MainDish> getMainDishes() {
		return mainDishes;
	}

	/** 
	 * Sets the set of main dishes in the menu
	 * @param mainDishes the new set of main dishes
	 */
	public void setMainDishes(Set<MainDish> mainDishes) {
		this.mainDishes = mainDishes;
	}

	/** 
	 * @return the set of desserts in the menu
	 */
	public Set<Dessert> getDesserts() {
		return desserts;
	}

	/** 
	 * Sets the set of desserts in the menu
	 * @param desserts the new set of desserts
	 */
	public void setDesserts(Set<Dessert> desserts) {
		this.desserts = desserts;
	}

	/** 
	 * Returns a set of all dishes in the menu, including starters, main dishes, and desserts.
	 * 
	 * @return a set containing all dishes in the menu
	 */
	public Set<Dish> getDishes() {
		Set<Dish> dishes = new HashSet<>();
		dishes.addAll(starters);
		dishes.addAll(mainDishes);
		dishes.addAll(desserts);
		return dishes;
	}

	/**
     * Returns a dish by its name from a specific restaurant's menu.
     * 
     * @param dishName
     * @return
     */
    public Dish getDishByName(String dishName) {
        // Search for the dish in the restaurant's menu
        for (Dish dish : this.getDishes()) {
            if (dish.getName().equalsIgnoreCase(dishName)) {
                return dish; // Dish found, no exception thrown
            }
        }
        // If no dish is found, throw an exception
        return null;
        
    }

	/**
	 * Returns a meal by its name from the menu.
	 * 
	 * @param mealName the name of the meal to search for
	 * @return the Meal object if found, or null if not found
	 */
	public Meal getMealByName(String mealName) {
		for (Meal meal : meals) {
			if (meal.getName().equalsIgnoreCase(mealName)) {
				return meal; // Meal found, no exception thrown
			}
		}
		// If no meal is found, return null
		return null;
	}

	/** 
	 * @return the set of meals in the menu
	 */
	public Set<Meal> getMeals() {
		return meals;
	}

	/** 
	 * Sets the set of meals in the menu
	 * @param meals the new set of meals
	 */
	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}

	/** 
	 * @return the general discount rate applied to meals
	 */
	public double getGeneralDiscount() {
		return generalDiscount;
	}

	/** 
	 * @return the special discount rate for "Meal of the Week"
	 */
	public double getSpecialDiscount() {
		return mealOfTheWeekDiscount;
	}

	/** 
	 * Sets the special discount rate for "Meal of the Week" and updates related meals' pricing strategy
	 * @param mealOfTheWeekDiscount the new special discount rate
	 */
	public void setSpecialDiscount(double mealOfTheWeekDiscount) {
		if (mealOfTheWeekDiscount < 0 || mealOfTheWeekDiscount > 1) {
			throw new IllegalArgumentException("Discount must be between 0 and 1");
		}
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
		
		for (Meal meal : meals) {
			if (meal.isMealOfTheWeek()) {
				meal.setPricingStrategy(new MealOfTheWeekDiscount(this.mealOfTheWeekDiscount));
			}
		}
	}
	
	/** 
	 * Sets the general discount rate and updates meals currently using general discount pricing strategy
	 * @param generalDiscount the new general discount rate
	 */
	public void setGeneralDiscount(double generalDiscount) {
		if (generalDiscount < 0 || generalDiscount > 1) {
			throw new IllegalArgumentException("Discount must be between 0 and 1");
		}
		this.generalDiscount = generalDiscount;
		
		for (Meal meal : meals) {
			if (meal.pricingMealStrategy instanceof GeneralDiscountMeal) {
				meal.setPricingStrategy(new GeneralDiscountMeal(this.generalDiscount));
			}
		}
	}
	
	/** 
	 * Adds a dish to the appropriate category (starter, main dish, or dessert)
	 * @param dish the dish to add
	 */
	public void addDish(Dish dish) {
		if (dish instanceof Starter) {
			Starter starter = (Starter) dish;
			this.starters.add(starter);
		}
		else if (dish instanceof MainDish) {
			MainDish mainDish = (MainDish) dish;
			this.mainDishes.add(mainDish);
		}
		else if (dish instanceof Dessert) {
			Dessert dessert = (Dessert) dish;
			this.desserts.add(dessert);
		}
	}
	
	/** 
	 * Removes a dish from the appropriate category (starter, main dish, or dessert)
	 * @param dish the dish to remove
	 */
	public void removeDish(Dish dish) {
		if (dish instanceof Starter) {
			if (!this.starters.contains(dish)) {
				throw new IllegalArgumentException("Dish not found in the menu");
			}
			Starter starter = (Starter) dish;
			this.starters.remove(starter);
		}
		else if (dish instanceof MainDish) {
			if (!this.mainDishes.contains(dish)) {
				throw new IllegalArgumentException("Dish not found in the menu");
			}
			MainDish mainDish = (MainDish) dish;
			this.mainDishes.remove(mainDish);
		}
		else if (dish instanceof Dessert) {
			if (!this.desserts.contains(dish)) {
				throw new IllegalArgumentException("Dish not found in the menu");
			}
			Dessert dessert = (Dessert) dish;
			this.desserts.remove(dessert);
		}
	}
	
	/** 
	 * Adds a meal to the menu
	 * @param meal the meal to add
	 */
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}
	
	/** 
	 * Removes a meal from the menu
	 * @param meal the meal to remove
	 */
	public void removeMeal(Meal meal) {
		if (!this.meals.contains(meal)) {
			throw new IllegalArgumentException("Meal not found in the menu");
		}
		this.meals.remove(meal);
	}
	
	@Override
	public String toString() {
		String returnString = "";
		
		returnString += "------------ STARTERS -----------\n";
		for (Starter dish : this.starters) {
			returnString += dish.toString() + "\n";
		}
		
		returnString += "---------- MAIN DISHES ----------\n";
		for (MainDish dish : this.mainDishes) {
			returnString += dish.toString() + "\n";
		}
		
		returnString += "------------ DESSERTS -----------\n";
		for (Dessert dish : this.desserts) {
			returnString += dish.toString() + "\n";
		}
		
		returnString += "-------------- MEALS ------------\n";
		for (Meal meal : this.meals) {
			returnString += meal.toString() + "\n";
		}
		
		return returnString;
	}
}
