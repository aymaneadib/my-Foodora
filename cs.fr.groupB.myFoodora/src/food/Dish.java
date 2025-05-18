package food;

public abstract class Dish {

	protected String name;
	protected double price;
	protected boolean isVegetarian;
	protected boolean isGlutenFree;
	protected int frequencyDelivery;
	
	public Dish(String name, double price, boolean isVegetarian, boolean isGlutenFree) {
		this.name = name;
		this.price = price;
		this.isVegetarian = isVegetarian;
		this.isGlutenFree = isGlutenFree;
		this.frequencyDelivery = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	public void setGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

	public int getFrequencyDelivery() {
		return frequencyDelivery;
	}

	public void setFrequencyDelivery(int frequencyDelivery) {
		this.frequencyDelivery = frequencyDelivery;
	}


	public void incrementFrequencyDelivery() {
		this.frequencyDelivery++;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Dish) {
			Dish otherDish = (Dish) other;
			result = (this.name == otherDish.getName());
		}
	    
	    return result;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
