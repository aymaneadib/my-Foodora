package test;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fidelity.*;
import food.BadMealFormulaException;
import food.Dessert;
import food.Dish;
import food.HalfMeal;
import food.MainDish;
import food.UnrecognizedDishException;
import order.Order;
import user.BadUserCreationException;
import user.Courier;
import user.Customer;
import user.Location;
import user.Restaurant;

/**
 * Test class for FidelityCard classes.
 * 
 * @author Alisson Bonatto
 */
public class TestFidelityCards {

	static Customer customerLucas;
	static Restaurant restaurant1;
	static Courier courier1;
	static Order order;
	static double x;
	
	@BeforeClass
	public static void initializeTests() throws BadUserCreationException, BadMealFormulaException, UnrecognizedDishException {
		customerLucas = new Customer("Lucas", "Petit", "lucaspetit", "1234", "+33777777777", "lucas.petit@email.com", new Location(0, 0));
		restaurant1 = new Restaurant("RestaurantParis", "restoparis", "1234", new Location(0.1, 0.1));
		courier1 = new Courier("Bernard", "Petit", "bernardpetit", "1234", "+333", new Location(5, 9));
		
		// Adding dishes and meals to restaurant1 menu
		MainDish dish1 = new MainDish("Dish1", 100, true, false);
		Dessert dish2 = new Dessert("Dish2", 200, false, false);
		HashSet<Dish> dishes = new HashSet<Dish>();
		dishes.add(dish1);
		dishes.add(dish2);
		HalfMeal halfMeal = new HalfMeal("HalfMeal1", dishes);
		restaurant1.addDish(dish1);
		restaurant1.addDish(dish2);
		restaurant1.addMeal(halfMeal);
		
		// Creating Order
		order = new Order(customerLucas, restaurant1, courier1);
		order.addDish(dish1);
		order.addDish(dish2);
		order.addMeal(halfMeal);
	}
	
	@Test
	public void testBasicCardConstructor() {
		BasicCard basicCard = new BasicCard(customerLucas);
		
		Assert.assertTrue(basicCard.getOwner().equals(customerLucas));
	}
	
	@Test
	public void testBasicCardFinalPrice() {
		BasicCard basicCard = new BasicCard(customerLucas);
		double expectedFinalPrice = (100 + 200) * (1 + 0.95);
		
		Assert.assertTrue(basicCard.getFinalPrice(order) >= expectedFinalPrice*0.99 && basicCard.getFinalPrice(order) <= expectedFinalPrice*1.01);
	}
	
	@Test
	public void testBasicCardToString() {
		BasicCard basicCard = new BasicCard(customerLucas);

		Assert.assertTrue(basicCard.toString().equals("BasicCard"));
	}
	
	@Test
	public void testPointCardConstructor() {
		PointCard pointCard = new PointCard(customerLucas);

		Assert.assertTrue(pointCard.getOwner().equals(customerLucas));
		Assert.assertTrue(pointCard.getMoneySpent() == 0);
		Assert.assertTrue(pointCard.getPoints() == 0);
	}
	
	@Test
	public void testPointCardFinalPrice() {
		PointCard pointCard = new PointCard(customerLucas);
		pointCard.addMoneySpent(1000);
		double expectedFinalPrice = (100 + 200) * (1 + 0.95) * 0.9;
		double retunedPrice = pointCard.getFinalPrice(order);
		
		Assert.assertTrue(retunedPrice >= expectedFinalPrice*0.99 && retunedPrice <= expectedFinalPrice*1.01);
	}
	
	@Test
	public void testPointCardToString() {
		PointCard pointCatd = new PointCard(customerLucas);

		Assert.assertTrue(pointCatd.toString().equals("PointCard"));
	}
	
	@Test
	public void testLotteryCardConstructor() {
		LotteryCard lotteryCard = new LotteryCard(customerLucas);
		LotteryCard lotteryCardHighProbability = new LotteryCard(customerLucas, 0.9);

		Assert.assertTrue(lotteryCard.getOwner().equals(customerLucas));
		Assert.assertTrue(lotteryCard.getProbability() == 0.005);
		
		Assert.assertTrue(lotteryCardHighProbability.getProbability() == 0.9);
	}
	
	@Test
	public void testLotteryCardToString() {
		LotteryCard lotteryCard = new LotteryCard(customerLucas);

		Assert.assertTrue(lotteryCard.toString().equals("LotteryCard"));
	}
	
	@Test
	public void testCardFactory() {
		FidelityCard basicCard = FidelityCardFactory.createFidelityCard("basicCard", customerLucas);
		FidelityCard pointCard = FidelityCardFactory.createFidelityCard("pointCard", customerLucas);
		FidelityCard lotteryCard = FidelityCardFactory.createFidelityCard("lotteryCard", customerLucas);
		
		Assert.assertTrue(basicCard instanceof BasicCard);
		Assert.assertTrue(pointCard instanceof PointCard);
		Assert.assertTrue(lotteryCard instanceof LotteryCard);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCardFactoryUnrecogizedCardType() {
		@SuppressWarnings("unused")
		FidelityCard card = FidelityCardFactory.createFidelityCard("UltraCard", customerLucas);
	}
	
}
