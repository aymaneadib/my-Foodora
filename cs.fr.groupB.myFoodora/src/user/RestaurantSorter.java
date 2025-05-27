package user;

import java.util.ArrayList;

/**
 * The RestaurantSorter class provides a method to sort a list of Restaurant objects
 * based on their order counter. It uses the RestaurantComparator class for comparison.
 * 
 * @author Aymane Adib
 */
public class RestaurantSorter {
	/**
	 * Sorts a list of Restaurant objects based on their order counter.
	 *
	 * @param restaurants the list of Restaurant objects to be sorted
	 * @return a sorted list of Restaurant objects
	 */
	public ArrayList<Restaurant> sort (ArrayList<Restaurant> restaurants){
		ArrayList<Restaurant> sortedRestaurants = new ArrayList<Restaurant>(restaurants);
		sortedRestaurants.sort(new RestaurantComparator());
		return sortedRestaurants;
	}
}