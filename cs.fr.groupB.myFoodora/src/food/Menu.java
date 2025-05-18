package food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Menu {
	
	private Set<Starter> starters;
	private Set<MainDish> mainDishes;
	private Set<Dessert> desserts;
	private Set<Meal> meals;
	private double genericDiscout;
	private double mealOfTheWeekDiscount;
	
	public Menu(Set<Starter> starters, Set<MainDish> mainDishes, Set<Dessert> desserts, Set<Meal> meals,
			double genericDiscout, double mealOfTheWeekDiscount) {
		this.starters = starters;
		this.mainDishes = mainDishes;
		this.desserts = desserts;
		this.meals = meals;
		this.genericDiscout = genericDiscout;
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
	}
	
	public Menu(double genericDiscout, double mealOfTheWeekDiscount) {
		this.starters = new HashSet<Starter>();
		this.mainDishes = new HashSet<MainDish>();
		this.desserts = new HashSet<Dessert>();
		this.meals = new HashSet<Meal>();
		this.genericDiscout = genericDiscout;
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
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

	public double getGenericDiscout() {
		return genericDiscout;
	}

	public double getMealOfTheWeekDiscount() {
		return mealOfTheWeekDiscount;
	}

	public void setMealOfTheWeekDiscount(double mealOfTheWeekDiscount) {
		this.mealOfTheWeekDiscount = mealOfTheWeekDiscount;
		
		for (Meal meal : meals) {
			if (meal.isMealOfTheWeek()) {
				meal.setPricingStrategy(new MealOfTheWeekDiscount(this.mealOfTheWeekDiscount));
			}
		}
	}
	
	public void setGenericDiscout(double genericDiscout) {
		this.genericDiscout = genericDiscout;
		
		for (Meal meal : meals) {
			if (meal.pricingMealStrategy instanceof GeneralDiscountMeal) {
				meal.setPricingStrategy(new GeneralDiscountMeal(this.genericDiscout));
			}
		}
	}
}
