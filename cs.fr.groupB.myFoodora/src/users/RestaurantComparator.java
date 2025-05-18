package users;

import java.util.Comparator;

/**
 * Comparator class for comparing two Restaurant objects based on their order counter.
 * This class implements the Comparator interface and provides a method to compare
 * two Restaurant objects.
 * 
 * @author Aymane Adib
 */
public class RestaurantComparator implements Comparator<Restaurant> {
	/**
	 * Compares two Restaurant objects based on their order counter.
	 *
	 * @param r1 the first Restaurant object
	 * @param r2 the second Restaurant object
	 * @return a negative integer, zero, or a positive integer as the first argument
	 *         is less than, equal to, or greater than the second
	 */
	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		return r2.getOrderCounter()-r1.getOrderCounter();
	}
}