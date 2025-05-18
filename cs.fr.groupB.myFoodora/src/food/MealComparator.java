package food;

import java.util.Comparator;

/**
 * Comparator class to compare Meal objects based on their delivery frequency.
 * Compares two meals by their frequencyDelivery attribute.
 * 
 * @author Alisson Bonatto
 */
public class MealComparator implements Comparator<Meal>{

	@Override
	public int compare(Meal meal1, Meal meal2) {
		return (meal1.getFrequencyDelivery() - meal2.getFrequencyDelivery());
	}

}
