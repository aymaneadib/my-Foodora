package test;

import org.junit.*;
import org.junit.Test;
import food.*;

import java.util.Set;

/**
 * Test class for Menu.
 * This class contains unit tests for the Menu class.
 * 
 * @author Aymane Adib
 */
public class TestMenu {

    static Menu menu;

    @BeforeClass
    public static void setUp() {
        menu = new Menu();
    }

    @Test
    public void testMenuCreation() {
        Assert.assertNotNull(menu);
    }

    @Test
    public void testAddDish() {
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

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentDishThrowsException() {
        Dish dish = new MainDish("Pza", 10, false, false);
        menu.removeDish(dish); // Should throw IllegalArgumentException
    }

    @Test
    public void testAddMeal() throws Exception {
        Dish dish1 = new MainDish("Pizza", 10, false, false);
        Dish dish2 = new Dessert("Gelato", 5.99, true, false);
        Meal meal = new HalfMeal("Half Meal", Set.of(dish1, dish2));
        menu.addMeal(meal);
        Assert.assertTrue(menu.getMeals().contains(meal));
    }

    @Test
    public void testRemoveMeal() throws Exception {
        Dish dish1 = new MainDish("Pizza", 10, false, false);
        Dish dish2 = new Dessert("Gelato", 5.99, true, false);
        Meal meal = new HalfMeal("Half Meal", Set.of(dish1, dish2));
        menu.addMeal(meal);
        menu.removeMeal(meal);
        Assert.assertFalse(menu.getMeals().contains(meal));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentMealThrowsException() throws Exception {
        Dish dish1 = new MainDish("Pizza", 10, false, false);
        Dish dish2 = new Dessert("Gelato", 5.99, true, false);
        Meal meal = new HalfMeal("Meal", Set.of(dish1, dish2));
        menu.removeMeal(meal); // Should throw IllegalArgumentException
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
    public void testGetMeals() throws Exception {
        Menu menu = new Menu();
        Dish dish1 = new MainDish("Pizza", 10, false, false);
        Dish dish2 = new Dessert("Gelato", 5.99, true, false);
        Meal meal = new HalfMeal("Half Meal", Set.of(dish1, dish2));
        menu.addMeal(meal);
        Assert.assertTrue(menu.getMeals().contains(meal));
    }

    @Test
    public void testGetGeneralDiscount() {
        Menu menu = new Menu();
        Assert.assertEquals(0.05, menu.getGeneralDiscount(), 0.0001);
        menu.setGeneralDiscount(0.15);
        Assert.assertEquals(0.15, menu.getGeneralDiscount(), 0.0001);
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
        menu.setGeneralDiscount(1.5);
    }

    @Test
    public void testGetSpecialDiscount() {
        Assert.assertEquals(0.1, menu.getSpecialDiscount(), 0.0001);
        menu.setSpecialDiscount(0.2);
        Assert.assertEquals(0.2, menu.getSpecialDiscount(), 0.0001);
    }

    @Test
    public void testSpecialDiscount() {
        Assert.assertEquals(0.2, menu.getSpecialDiscount(), 0.0001);
        menu.setSpecialDiscount(0.2);
        Assert.assertEquals(0.2, menu.getSpecialDiscount(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeSpecialDiscountThrowsException() {
        menu.setSpecialDiscount(-0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSpecialDiscountGreaterThanOneThrowsException() {
        Menu menu = new Menu();
        menu.setSpecialDiscount(1.2);
    }

}