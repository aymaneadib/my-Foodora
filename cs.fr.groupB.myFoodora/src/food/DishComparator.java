package food;

import java.util.Comparator;

public class DishComparator implements Comparator<Dish>{

	@Override
	public int compare(Dish dish1, Dish dish2) {
		return (dish1.getFrequencyDelivery() - dish2.getFrequencyDelivery());
	}
	
}
