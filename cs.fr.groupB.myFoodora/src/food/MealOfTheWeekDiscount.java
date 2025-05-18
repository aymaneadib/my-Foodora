package food;

import java.util.Set;

public class MealOfTheWeekDiscount implements PricingMealStrategy{

	private double discount;

	public MealOfTheWeekDiscount(double discount) {
		this.discount = discount;
	}
	
	public MealOfTheWeekDiscount() {
		this.discount = 0.1;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public double getTotal(Set<Dish> dishes) {
		double total = 0;
		
		for (Dish dish : dishes) {
			total += dish.price;
		}
		
		return total*this.discount;
	}
	
}
