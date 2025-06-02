package test;

import org.junit.*;
import org.junit.Test;
import food.*;

/**
 * Test class for Menu.
 * This class contains unit tests for the Menu class.
 * 
 * @author Aymane Adib
 */
public class TestMenu {

    @Test
    public void testMenuCreation() {
        Menu menu = new Menu();
        Assert.assertNotNull(menu);
    }

    @Test
    public void testAddDish() {
        Menu menu = new Menu();
        Dish dish = new MainDish("Pizza", 10, false, false);
        menu.addDish(dish);
        Assert.assertTrue(menu.getMainDishes().contains(dish));
        dish = new Dessert("Gelato", 5.99, true, false);
        menu.addDish(dish);
        Assert.assertTrue(menu.getDesserts().contains(dish));
    }

    @Test
    public void testRemoveDish() {
        Menu menu = new Menu();
        Dish dish = new MainDish("Pizza", 10, false, false);
        menu.addDish(dish);
        menu.removeDish(dish);
        Assert.assertFalse(menu.getMainDishes().contains(dish));
    }

    @Test
    public void testGetDishes() {
        Menu menu = new Menu();
        Dish dish1 = new MainDish("Pizza", 10, false, false);
        Dish dish2 = new Dessert("Gelato", 5.99, true, false);
        menu.addDish(dish1);
        menu.addDish(dish2);
        Assert.assertTrue(menu.getMainDishes().contains(dish1));
        Assert.assertTrue(menu.getDesserts().contains(dish2));
    }

    @Test
    public void testGeneralDiscount() {
        Menu menu = new Menu();
        Assert.assertEquals(0.05, menu.getGeneralDiscount(), 0.0001);
        menu.setGeneralDiscount(0.1);
        Assert.assertEquals(0.1, menu.getGeneralDiscount(), 0.0001);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeDiscountThrowsException() {
        Menu menu = new Menu();
        menu.setGeneralDiscount(-0.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiscountGreaterThanOneThrowsException() {
        Menu menu = new Menu();
        menu.setSpecialDiscount(1.5);
    }

}