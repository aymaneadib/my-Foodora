package food;

import java.util.Set;

public class GeneralDiscountMeal implements PricingMealStrategy{

	private double discount;

	public GeneralDiscountMeal(double discount) {
		this.discount = discount;
	}
	
	public GeneralDiscountMeal() {
		this.discount = 0.05;
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
