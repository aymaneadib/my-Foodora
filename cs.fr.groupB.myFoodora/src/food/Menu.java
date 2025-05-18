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
	
	public Menu(Set<Starter> starters, Set<MainDish> mainDishes, Set<Dessert> desserts, Set<Meal> meals,
			double genericDiscout, double mealOfTheWeekDiscount) {
		this.starters = starters;
		this.mainDishes = mainDishes;
		this.desserts = desserts;
		this.meals = meals;
		this.generalDiscount = genericDiscout;
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
	}
	
	public Menu(double genericDiscout, double mealOfTheWeekDiscount) {
		this.starters = new HashSet<Starter>();
		this.mainDishes = new HashSet<MainDish>();
		this.desserts = new HashSet<Dessert>();
		this.meals = new HashSet<Meal>();
		this.generalDiscount = genericDiscout;
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
	}
	
	public Menu() {
		this.starters = new HashSet<Starter>();
		this.mainDishes = new HashSet<MainDish>();
		this.desserts = new HashSet<Dessert>();
		this.meals = new HashSet<Meal>();
		this.generalDiscount = 0;
		this.mealOfTheWeekDiscount = 0;
	}

	public Set<Starter> getStarters() {
		return starters;
	}

	public void setStarters(Set<Starter> starters) {
		this.starters = starters;
	}

	public Set<MainDish> getMainDishes() {
		return mainDishes;
	}

	public void setMainDishes(Set<MainDish> mainDishes) {
		this.mainDishes = mainDishes;
	}

	public Set<Dessert> getDesserts() {
		return desserts;
	}

	public void setDesserts(Set<Dessert> desserts) {
		this.desserts = desserts;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}

	public double getGeneralDiscount() {
		return generalDiscount;
	}

	public double getSpecialDiscount() {
		return mealOfTheWeekDiscount;
	}

	public void setSpecialDiscount(double mealOfTheWeekDiscount) {
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
		
		for (Meal meal : meals) {
			if (meal.isMealOfTheWeek()) {
				meal.setPricingStrategy(new MealOfTheWeekDiscount(this.mealOfTheWeekDiscount));
			}
		}
	}
	
	public void setGeneralDiscount(double generalDiscount) {
		this.generalDiscount = generalDiscount;
		
		for (Meal meal : meals) {
			if (meal.pricingMealStrategy instanceof GeneralDiscountMeal) {
				meal.setPricingStrategy(new GeneralDiscountMeal(this.generalDiscount));
			}
		}
	}
	
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
			Dessert desser = (Dessert) dish;
			this.desserts.add(desser);
		}
	}
	
	public void removeDish(Dish dish) {
		if (dish instanceof Starter) {
			Starter starter = (Starter) dish;
			this.starters.remove(starter);
		}
		else if (dish instanceof MainDish) {
			MainDish mainDish = (MainDish) dish;
			this.mainDishes.remove(mainDish);
		}
		else if (dish instanceof Dessert) {
			Dessert desser = (Dessert) dish;
			this.desserts.remove(desser);
		}
	}
	
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}
	
	public void removeMeal(Meal meal) {
		this.meals.remove(meal);
	}
}
