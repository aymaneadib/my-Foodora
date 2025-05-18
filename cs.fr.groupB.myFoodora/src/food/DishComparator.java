package food;

import java.util.Comparator;

/**
 * Comparator implementation for Dish objects.
 * Compares two Dish instances based on their delivery frequency.
 * 
 * @author Alisson Bonatto
 */
public class DishComparator implements Comparator<Dish> {

    /**
     * Compares two Dish objects by their frequencyDelivery attribute.
     * 
     * @param dish1 the first Dish to be compared
     * @param dish2 the second Dish to be compared
     * @return a negative integer, zero, or a positive integer as the first argument
     *         has less than, equal to, or greater than the second in delivery frequency
     */
    @Override
    public int compare(Dish dish1, Dish dish2) {
        return dish1.getFrequencyDelivery() - dish2.getFrequencyDelivery();
    }
}
