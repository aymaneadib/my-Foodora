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

    private static Dish maindish;
    private static Dish starter;
    private static Dish dessert;
    private static Meal fullMeal;
    private static Meal halfMeal;
    private static Meal halfMeal2;

    @BeforeClass
    public static void setUp() throws Exception {
        maindish = new MainDish("Pizza",10, false, false);
        starter = new Starter("Salad", 3, false, true);
        dessert = new Dessert("Gelato", 5.99, true, false);
        fullMeal = new FullMeal("Full Meal", Set.of(maindish, starter, dessert));
        halfMeal = new HalfMeal("Half Meal", Set.of(maindish, dessert));
        halfMeal2 = new HalfMeal("Half Meal 2", Set.of(maindish, starter));
    }

    @AfterClass
    public static void tearDown() {
        maindish = null;
        starter = null;
        dessert = null;
        fullMeal = null;
        halfMeal = null;
        halfMeal2 = null;
    }

    @Test
    public void testFullMealCreation() throws Exception {
        Assert.assertNotNull(fullMeal);
        Assert.assertEquals("Full Meal", fullMeal.getName());
    }

    @Test
    public void testHalfMealCreation() throws Exception {
        Assert.assertNotNull(halfMeal);
        Assert.assertEquals("Half Meal", halfMeal.getName());
        Assert.assertEquals("Half Meal 2", halfMeal2.getName());
    }

    @Test(expected = BadMealFormulaException.class)
    public void testBadMealFormulaCreation() throws Exception {
        Meal badMeal = new HalfMeal("Bad Meal", Set.of(maindish, starter, dessert));
    }

    @Test
    public void testMealFactory() throws Exception {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        MealFactory mealFactory = new MealFactory();
        Meal fullMeal = mealFactory.createMeal("FullMeal", "Full Meal", Set.of(maindish, starter, dessert));
        Meal halfMeal = mealFactory.createMeal("HalfMeal","Half Meal 1", Set.of(maindish, dessert));
        Meal halfMeal2 = mealFactory.createMeal("haLfMEaL","Half Meal 2", Set.of(maindish, starter));
        Assert.assertNotNull(fullMeal);
        Assert.assertNotNull(halfMeal);
        Assert.assertNotNull(halfMeal2);
    }

    @Test(expected = BadMealTypeCreationException.class)
    public void testBadMealTypeCreationException() throws BadMealTypeCreationException, BadNumberOfArgumentsException,
            BadArgumentTypeException, UnrecognizedDishException, BadMealFormulaException {
        MealFactory mealFactory = new MealFactory();
        Meal badMeal = mealFactory.createMeal("BadMeal", "Bad Meal", Set.of(maindish, starter, dessert));
    }

    @Test(expected = BadMealFormulaException.class)
    public void testBadMealTypeCreation() throws BadMealTypeCreationException, BadNumberOfArgumentsException,
            BadArgumentTypeException, UnrecognizedDishException, BadMealFormulaException {
        @SuppressWarnings("unused")
		Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter1 = new  Starter("Eggs", 3, false, true);
        Dish starter2 = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        MealFactory mealFactory = new MealFactory();
        @SuppressWarnings("unused")
		Meal fullMeal = mealFactory.createMeal("FullMeal", "Full Meal", Set.of(starter1, starter2, dessert));
        @SuppressWarnings("unused")
		Meal halfMeal = mealFactory.createMeal("HalfMeal","Half Meal 1", Set.of(starter1, dessert));
    }

    @Test
    public void testMealPriceUpdate() throws Exception{
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 3, false, true);
        Dish dessert = new Dessert("Gelato", 5.99, true, false);
        Meal fullMeal = new FullMeal("Full Meal", Set.of(maindish, starter, dessert));
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
        Meal fullMeal1 = new FullMeal("Full Meal", Set.of(maindish, starter, dessert));
        Meal fullMeal2 = new FullMeal("Full Meal", Set.of(maindish, starter, dessert));
        Assert.assertEquals(fullMeal1, fullMeal2);
    }

    @Test
    public void testPricingStrategy() throws Exception {
        Dish maindish = new MainDish("Pizza",10, false, false);
        Dish starter = new  Starter("Salad", 5, false, true);
        Dish dessert = new Dessert("Gelato", 5, true, false);
        Meal fullMeal = new FullMeal("Full Meal", Set.of(maindish, starter, dessert));
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