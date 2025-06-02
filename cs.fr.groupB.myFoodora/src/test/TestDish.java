package test;

import food.*;
import org.junit.*;
import org.junit.Test;


/**
 * Test class for the Dish class and its subclasses.
 * 
 * @author Aymane ADIB
 */
public class TestDish {

    @Test
    public void testDishCreation() {
        Dish dish1 = new Dessert("Gelato", 5.99, true, false);
        Dish dish2 = new MainDish("Pizza",10, false, false);
        Dish dish3 = new Starter("Salad", 3, false, true);
        Assert.assertNotNull(dish1);
        Assert.assertEquals("Gelato", dish1.getName());
        Assert.assertEquals(5.99, dish1.getPrice(), 0.01);
        Assert.assertEquals("Pizza", dish2.getName());
        Assert.assertEquals(10, dish2.getPrice(), 0.01);
        Assert.assertEquals(false, dish2.isVegetarian());
        Assert.assertEquals(false, dish2.isGlutenFree());
        Assert.assertNotNull(dish3);
    }

    @Test
    public void testDishPriceUpdate() {
        Dish dish = new MainDish("Burger", 8.99, true, false);
        dish.setPrice(9.99);
        Assert.assertEquals(9.99, dish.getPrice(), 0.01);
        dish.incrementFrequencyDelivery();
        Assert.assertEquals(1, dish.getFrequencyDelivery());
    }

    @Test
    public void testEquals() {
        Dish dish1 = new Dessert("Gelato", 5.99, true, false);
        Dish dish2 = new Dessert("Gelato", 5.99, true, false);
        Dish dish3 = new MainDish("Pizza", 10, false, false);
        Assert.assertTrue(dish1.equals(dish2));
        Assert.assertFalse(dish1.equals(dish3));
    }

    @Test
    public void testCompareTo() {
        DishComparator comparator = new DishComparator();
        Dish dish1 = new Dessert("Gelato", 5.99, true, false);
        Dish dish2 = new Starter("Tiramisu", 6.99, true, false);
        Assert.assertTrue(comparator.compare(dish1, dish2)<=0);
    }

    @Test
    public void testGetFrequencyDelivery() {
        Dish dish = new Dessert("Gelato", 5.99, true, false);
        Assert.assertEquals(0, dish.getFrequencyDelivery());
        dish.incrementFrequencyDelivery();
        Assert.assertEquals(1, dish.getFrequencyDelivery());
        dish.decrementFrequencyDelivery();
        Assert.assertEquals(0, dish.getFrequencyDelivery());
    }

    @Test
    public void testDishFactory() throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
        DishFactory factory = new DishFactory();
        Dish dish1 = factory.createDish("Dessert", "Gelato", 5.99, true, false);
        Dish dish2 = factory.createDish("MainDish", "Pizza", 10.0, false, false);
        Assert.assertTrue(dish1 instanceof Dessert);
        Assert.assertTrue(dish2 instanceof MainDish);
    }
}