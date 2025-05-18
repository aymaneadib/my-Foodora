package food;

import java.util.Set;

public abstract class Meal {
    
	protected String name;
	protected Set<Dish> dishes;
	protected boolean isGlutenFree;
	protected boolean isVegetarian;
	protected int frequencyDelivery;
	protected PricingMealStrategy pricingMealStrategy;
	
	public Meal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian) {
		this.name = name;
		this.dishes = dishes;
		this.isGlutenFree = verifyGlutenFree(dishes);
		this.isVegetarian = verifyVegetarian(dishes);
		this.frequencyDelivery = 0;
		this.pricingMealStrategy = new GeneralDiscountMeal();
	}
	
	public Meal(String name, Set<Dish> dishes, boolean isGlutenFree, boolean isVegetarian, PricingMealStrategy pricingStrategy) {
		this.name = name;
		this.dishes = dishes;
		this.isGlutenFree = verifyGlutenFree(dishes);
		this.isVegetarian = verifyVegetarian(dishes);
		this.frequencyDelivery = 0;
		this.pricingMealStrategy = pricingStrategy;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public int getFrequencyDelivery() {
		return frequencyDelivery;
	}

	public void setFrequencyDelivery(int frequencyDelivery) {
		this.frequencyDelivery = frequencyDelivery;
	}

	public PricingMealStrategy getPricingStrategy() {
		return pricingMealStrategy;
	}

	public void setPricingStrategy(PricingMealStrategy pricingStrategy) {
		this.pricingMealStrategy = pricingStrategy;
	}

	public void incrementFrequencyDelivery() {
		this.frequencyDelivery++;
	}
	
	public double getPrice() {
		return this.pricingMealStrategy.getTotal(dishes);
	}
	
	public boolean isMealOfTheWeek() {
		return (this.pricingMealStrategy instanceof MealOfTheWeekDiscount);
	}
	
	public void makeMealOfTheWeek(double discount) {
		this.pricingMealStrategy = new MealOfTheWeekDiscount(discount);
	}
	
	public void makeMealOfTheWeek() {
		this.pricingMealStrategy = new MealOfTheWeekDiscount();
	}
	
	public void removeMealOfTheWeek() {
		this.pricingMealStrategy = new GeneralDiscountMeal();
	}
	
	private boolean verifyGlutenFree(Set<Dish> dishes) {
		for(Dish dish : dishes) {
			if (!dish.isGlutenFree) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean verifyVegetarian(Set<Dish> dishes) {
		for(Dish dish : dishes) {
			if (!dish.isVegetarian) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Meal) {
			Meal otherMeal = (Meal) other;
			result = (this.name == otherMeal.getName());
		}
	    
	    return result;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
