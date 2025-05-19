package test;

import food.Dish;
import org.junit.*;


public class DishTest {

    @Test
    public void testDishCreation() {
        Dish dish = new Dish("Pizza", 10.99, "Delicious cheese pizza", 1);
        Assert.assertNotNull(dish);
        Assert.assertEquals("Pizza", dish.getName());
        Assert.assertEquals(10.99, dish.getPrice(), 0.01);
        Assert.assertEquals("Delicious cheese pizza", dish.getDescription());
        Assert.assertEquals(1, dish.getId());
    }

    @Test
    public void testDishPriceUpdate() {
        Dish dish = new Dish("Burger", 8.99, "Juicy beef burger", 2);
        dish.setPrice(9.99);
        Assert.assertEquals(9.99, dish.getPrice(), 0.01);
    }

    @Test
    public void testDishDescriptionUpdate() {
        Dish dish = new Dish("Pasta", 12.99, "Creamy Alfredo pasta", 3);
        dish.setDescription("Spicy Arrabbiata pasta");
        Assert.assertEquals("Spicy Arrabbiata pasta", dish.getDescription());
    }

}
