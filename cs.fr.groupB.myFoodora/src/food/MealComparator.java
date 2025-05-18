package food;

import java.util.Comparator;

public class MealComparator implements Comparator<Meal>{

	@Override
	public int compare(Meal meal1, Meal meal2) {
		return (meal1.getFrequencyDelivery() - meal2.getFrequencyDelivery());
	}

}
