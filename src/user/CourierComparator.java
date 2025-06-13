package user;

import java.util.Comparator;


/**
 * Comparator class for comparing Courier objects based on their delivery counter.
 * It implements the Comparator interface and overrides the compare method.
 * 
 * @author Aymane Adib
 */
public class CourierComparator implements Comparator<Courier> {
	/**
	 * Compares two Courier objects based on their delivery counter.
	 * 
	 * @param c1 the first Courier object
	 * @param c2 the second Courier object
	 * @return a negative integer, zero, or a positive integer as the first Courier
	 *         has less than, equal to, or greater than the second Courier's delivery counter
	 */
	@Override
	public int compare(Courier c1, Courier c2) {
		return c2.getDeliveryCounter()-c1.getDeliveryCounter();
	}
}