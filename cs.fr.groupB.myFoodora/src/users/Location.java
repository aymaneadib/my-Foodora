package users;

/**
 * Exception class for handling errors related to user creation.
 * This exception is thrown when there are issues with creating a user.
 * 
 * @author Aymane Adib
 */
public class Location {
    
    private double x;
    private double y;

    /**
     * Default constructor for Location.
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for Location with specified coordinates.
     *
     * @param x the x-coordinate of the location
     * @param y the y-coordinate of the location
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the location.
     *
     * @return the y-coordinate of the location
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the location.
     *
     * @param x the new x-coordinate of the location
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the location.
     *
     * @param y the new y-coordinate of the location
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Calculates the distance to another location.
     *
     * @param other the other location
     * @return the distance to the other location
     */
    public double distanceTo(Location other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    
    /**
     * Verifies if the argument location is equals to current location.
     * Two locations are equals if they have the same coordinates.
     *
     * @param location the other location to be compared with
     * @return true if the locations are equals
     */
    @Override
	public boolean equals(Object location) {
		boolean result = false;
		if (location instanceof Location) {
			Location otherLocation = (Location) location;
			result = (this.x == otherLocation.getX()) && (this.y == otherLocation.getY());
		}
	    
	    return result;
	}

    /**
     * String representation of the location.
     */
    @Override
    public String toString() {
        return "At (" + x + ", " + y + ")";
    }

}
