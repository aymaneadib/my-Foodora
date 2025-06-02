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
    public void testDessertCreation() {
        Dish dish1 = new Dessert("Gelato", 5.99, true, false);
        Assert.assertNotNull(dish1);
        Assert.assertEquals("Gelato", dish1.getName());
        Assert.assertEquals(5.99, dish1.getPrice(), 0.01);
    }

    @Test
    public void testMaindDishCreation() {
        Dish dish = new MainDish("Burger", 8.99, true, false);
        Assert.assertNotNull(dish);
        Assert.assertEquals("Burger", dish.getName());
        Assert.assertEquals(8.99, dish.getPrice(), 0.01);
        Assert.assertTrue(dish.isVegetarian());
        Assert.assertFalse(dish.isGlutenFree());
    }

    @Test
    public void testStarterCreation() {
        Dish dish = new Starter("Soup", 4.50, false, true);
        Assert.assertNotNull(dish);
        Assert.assertEquals("Soup", dish.getName());
        Assert.assertEquals(4.50, dish.getPrice(), 0.01);
        Assert.assertFalse(dish.isVegetarian());
        Assert.assertTrue(dish.isGlutenFree());
    }

    @Test
    public void testDishPriceUpdate() {
        Dish dish = new MainDish("Burger", 8.99, true, false);
        dish.setPrice(9.99);
        Assert.assertEquals(9.99, dish.getPrice(), 0.01);
        dish.incrementFrequencyDelivery();
        Assert.assertEquals(1, dish.getFrequencyDelivery());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDishPriceUpdateWithNegativeValue() throws IllegalArgumentException {
        Dish dish = new Dessert("Gelato", 5.99, true, false);
        dish.setPrice(-1.0); // Should throw IllegalArgumentException
    }

    @Test
    public void testDishVegetarianStatusUpdate() {
        Dish dish = new Starter("Salad", 3.99, true, false);
        dish.setVegetarian(false);
        Assert.assertFalse(dish.isVegetarian());
    }

    @Test
    public void testDishGlutenFreeStatusUpdate() {
        Dish dish = new MainDish("Pasta", 7.99, false, true);
        dish.setGlutenFree(false);
        Assert.assertFalse(dish.isGlutenFree());
    }

    @Test
    public void testDishNameUpdate() {
        Dish dish = new Dessert("Gelato", 5.99, true, false);
        dish.setName("Sorbet");
        Assert.assertEquals("Sorbet", dish.getName());
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

    @Test(expected = BadNumberOfArgumentsException.class)
    public void testDishFactoryWithBadNumberOfArguments() throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
        DishFactory factory = new DishFactory();
        factory.createDish("Dessert", "Gelato", 5.99); // Missing boolean arguments
    }

    @Test(expected = BadDishTypeCreationException.class)
    public void testDishFactoryWithBadDishType() throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
        DishFactory factory = new DishFactory();
        factory.createDish("InvalidType", "Gelato", 5.99, true, false); // Invalid dish type
    }

    @Test(expected = BadArgumentTypeException.class)
    public void testDishFactoryWithBadArgumentType() throws BadNumberOfArgumentsException, BadDishTypeCreationException, BadArgumentTypeException {
        DishFactory factory = new DishFactory();
        factory.createDish("Dessert", "Gelato", "five", true, false); // Price should be a double
    }
}