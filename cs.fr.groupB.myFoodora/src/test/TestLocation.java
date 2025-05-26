package test;

import org.junit.Assert;
import org.junit.Test;

import users.Location;

/**
 * Test class for Location class.
 * 
 * @author Alisson Bonatto
 */
public class TestLocation {

	@Test
	public void testLocationConstructor() {
		Location location = new Location(10, 15.2);
		Assert.assertTrue(location.getX() == 10);
		Assert.assertTrue(location.getY() == 15.2);
	}
	
	@Test
	public void testLocationSetters() {
		Location location = new Location(0, 0);
		location.setX(148.7);
		location.setY(33.1);
		
		Assert.assertTrue(location.getX() == 148.7);
		Assert.assertTrue(location.getY() == 33.1);
	}
	
	@Test
	public void testToString() {
		Location location = new Location(1, 2);
		Assert.assertTrue(location.toString().equals("At (" + location.getX() + ", " + location.getY() + ")"));
	}
	
	@Test
	public void testEquals() {
		Location location1 = new Location(22.2, 23.2);
		Location location2 = new Location(22.2, 23.2);
		
		Assert.assertTrue(location1.equals(location2));
	}
	
	@Test
	public void testDistanceTo() {
		Location location1 = new Location(0, 0);
		Location location2 = new Location(15, 12);
		double expectedDistance = Math.sqrt(Math.pow(15, 2) + Math.pow(12, 2));
		
		Assert.assertTrue(location1.distanceTo(location2) == expectedDistance);
	}
	
}
