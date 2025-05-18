package users;

import java.util.ArrayList;

/**
 * Class for sorting
 * Courier objects based on their delivery counter.
 * It contains a method to sort an ArrayList of Courier objects.
 * 
 * @author Aymane Adib
 */
public class CourierSorter {
	/**
	 * Sorts an ArrayList of Courier objects based on their delivery counter.
	 * 
	 * @param couriers the ArrayList of Courier objects to be sorted
	 * @return a new ArrayList of Courier objects sorted by delivery counter in descending order
	 */
	public ArrayList<Courier> sort (ArrayList<Courier> couriers){
		ArrayList<Courier> sortedCouriers = new ArrayList<Courier>(couriers);
		sortedCouriers.sort(new CourierComparator()); // Sort using the CourierComparator
		return sortedCouriers;
	}
}