package test;

import food.*;
import org.junit.*;


/**
 * Test class for the Dish class and its subclasses.
 * 
 * @author Aymane ADIB
 */
public class DishTest {

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
    public void testDishDescriptionUpdate() {
        Dish dish = new Dish("Pasta", 12.99, "Creamy Alfredo pasta", 3);
        dish.setDescription("Spicy Arrabbiata pasta");
        Assert.assertEquals("Spicy Arrabbiata pasta", dish.getDescription());
    }

}
