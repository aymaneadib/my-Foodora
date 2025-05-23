package test;

import org.junit.*;
import java.util.*;

import food.*;

/**
 * Test class for the Meal class and its subclasses.
 * 
 * @author Aymane ADIB
 */
public class TestMeal {

    @Test
    public void testMealCreation() throws Exception {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        Meal fullMeal = new FullMeal("Full Meal", Set.of(maindish, starter, dessert), false, true);
        Meal halfMeal = new HalfMeal("Half Meal", Set.of(maindish, dessert), false, true);
        Meal halfMeal2 = new HalfMeal("Half Meal 2", Set.of(maindish, starter), false, true);
        Assert.assertNotNull(fullMeal);
        Assert.assertNotNull(halfMeal);
        Assert.assertNotNull(halfMeal2);
        Assert.assertEquals("Full Meal", fullMeal.getName());
        Assert.assertEquals("Half Meal", halfMeal.getName());
        Assert.assertEquals("Half Meal 2", halfMeal2.getName());
        Assert.assertEquals(3, fullMeal.getDishes().size());
        Assert.assertEquals(2, halfMeal.getDishes().size());
    }

    @Test
    public void testMealFactory() throws Exception {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        MealFactory mealFactory = new MealFactory();
        Meal fullMeal = mealFactory.createMeal("FullMeal", "Full Meal", Set.of(maindish, starter, dessert), false, true);
        Meal halfMeal = mealFactory.createMeal("HalfMeal","Half Meal 1", Set.of(maindish, dessert), false, true);
        Meal halfMeal2 = mealFactory.createMeal("haLfMEaL","Half Meal 2", Set.of(maindish, starter), false, true);
        Assert.assertNotNull(fullMeal);
        Assert.assertNotNull(halfMeal);
        Assert.assertNotNull(halfMeal2);
    }

    @Test(expected = BadMealFormulaException.class)
    public void testBadMealTypeCreation() throws BadMealTypeCreationException, BadNumberOfArgumentsException,
            BadArgumentTypeException, UnrecognizedDishException, BadMealFormulaException {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter1 = new  Starter("Eggs", 3, false, true);
        Dish starter2 = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        MealFactory mealFactory = new MealFactory();
        Meal fullMeal = mealFactory.createMeal("FullMeal", "Full Meal", Set.of(starter1, starter2, dessert), false, true);
        Meal halfMeal = mealFactory.createMeal("HalfMeal","Half Meal 1", Set.of(starter1, dessert), false, true);
    }

    @Test
    public void testMealPriceUpdate() throws Exception{
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        Meal fullMeal = new FullMeal("Full Meal", Set.of(maindish, starter, dessert), false, true);
        fullMeal.makeMealOfTheWeek();
        Assert.assertTrue(fullMeal.isMealOfTheWeek());
        fullMeal.removeMealOfTheWeek();
        Assert.assertFalse(fullMeal.isMealOfTheWeek());
    }

    @Test
    public void testMealEquals() throws Exception {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        Meal fullMeal1 = new FullMeal("Full Meal", Set.of(maindish, starter, dessert), false, true);
        Meal fullMeal2 = new FullMeal("Full Meal", Set.of(maindish, starter, dessert), false, true);
        Assert.assertEquals(fullMeal1, fullMeal2);
    }

    @Test
    public void testPricingStrategy() throws Exception {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 5, false, true);
        Dish dessert = new Dessert("Gelato", 5, true, false);
        Meal fullMeal = new FullMeal("Full Meal", Set.of(maindish, starter, dessert), false, true);
        double price = fullMeal.getPrice();
        Assert.assertEquals(20*0.95,price,0.01);
        ((GeneralDiscountMeal)fullMeal.getPricingStrategy()).setDiscount(0.5);
        double newPrice = fullMeal.getPrice();
        Assert.assertEquals(20*0.5, newPrice, 0.01);
        fullMeal.setPricingStrategy(new MealOfTheWeekDiscount(0.5));
        double newPrice2 = fullMeal.getPrice();
        Assert.assertEquals(20*0.5, newPrice2, 0.01);
        ((MealOfTheWeekDiscount)fullMeal.getPricingStrategy()).setDiscount(0.3);
        double newPrice3 = fullMeal.getPrice();
        Assert.assertEquals(20*0.7, newPrice3, 0.01);
    }
}