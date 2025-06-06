package user;

import food.*;
import system.MyFoodora;
import order.Order;
import fidelity.*;

import java.util.*;

/**
 * Represents a Restaurant in the MyFoodora system.
 * This class extends {@link User} and provides functionalities for managing a restaurant's profile,
 * menu, discounts, and order history.
 *
 * @author Aymane Adib
 */
public class Restaurant extends User {

    private Location location;
    private Menu menu;
    private int orderCounter;

    /**
     * Constructs a new Restaurant instance.
     *
     * @param name     the name of the restaurant.
     * @param username the username for the restaurant's account.
     * @param password the password for the restaurant's account.
     * @param location the physical location of the restaurant.
     * @throws BadUserCreationException if an error occurs during user creation.
     */
    public Restaurant(String name, String username, String password, Location location) throws BadUserCreationException {
        super(name, username, password);
        this.location = location;
        this.menu = new Menu();
        this.orderCounter = 0; // Initialize order counter
    }

    /**
     * Retrieves a list of all orders that have been placed with this restaurant.
     *
     * @param system the main MyFoodora system instance which holds the global order history.
     * @return an {@link ArrayList} of {@link Order} objects associated with this restaurant.
     */
    public ArrayList<Order> orderHistory(MyFoodora system) {
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : system.getOrderHistory()) {
            if (order.getRestaurant().equals(this)) {
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Calculates the final price of an order for a given customer, applying their fidelity card discount.
     *
     * @param order    the order for which to calculate the price.
     * @param customer the customer who placed the order.
     * @return the final price of the order as a {@code double}.
     */
    public double getPrice(Order order, Customer customer) {
        FidelityCard card = customer.getFidelityCard();
        return card.getFinalPrice(order);
    }

    /**
     * Adds a new dish to the restaurant's menu.
     *
     * @param dish the {@link Dish} to be added to the menu.
     */
    public void addDish(Dish dish) {
        menu.addDish(dish);
    }

    /**
     * Removes a dish from the restaurant's menu.
     *
     * @param dish the {@link Dish} to be removed from the menu.
     */
    public void removeDish(Dish dish) {
        menu.removeDish(dish);
    }

    /**
     * Adds a new meal to the restaurant's menu.
     * A meal typically consists of several dishes.
     *
     * @param meal the {@link Meal} to be added to the menu.
     */
    public void addMeal(Meal meal) {
        menu.addMeal(meal);
    }

    /**
     * Removes a meal from the restaurant's menu.
     *
     * @param meal the {@link Meal} to be removed from the menu.
     */
    public void removeMeal(Meal meal) {
        menu.removeMeal(meal);
    }

    /**
     * Sets a general discount percentage for all items on the menu.
     * This discount is applied to meals that are not marked as special offers.
     *
     * @param discount the discount percentage (e.g., 0.1 for 10%).
     */
    public void setGeneralDiscount(double discount) {
        menu.setGeneralDiscount(discount);
    }

    /**
     * Sets a special discount for meals of the week.
     * This discount is applied only to meals designated as special offers.
     *
     * @param discount the special discount percentage (e.g., 0.2 for 20%).
     */
    public void setSpecialDiscount(double discount) {
        menu.setSpecialDiscount(discount);
    }

    /**
     * Gets the general discount percentage applied to the menu.
     *
     * @return the general discount as a {@code double}.
     */
    public double getGeneralDiscount() {
        return menu.getGeneralDiscount();
    }

    /**
     * Gets the special discount percentage for meals of the week.
     *
     * @return the special discount as a {@code double}.
     */
    public double getSpecialDiscount() {
        return menu.getSpecialDiscount();
    }

    /**
     * Gets the location of the restaurant.
     *
     * @return the {@link Location} of the restaurant.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Updates the location of the restaurant.
     *
     * @param location the new {@link Location} for the restaurant.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the restaurant's menu.
     *
     * @return the {@link Menu} object associated with this restaurant.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Replaces the restaurant's current menu with a new one.
     *
     * @param menu the new {@link Menu} for the restaurant.
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * Finds and returns a dish from the menu by its name.
     *
     * @param dishName the name of the dish to search for.
     * @return the {@link Dish} object if found, otherwise {@code null}.
     */
    public Dish getDishByName(String dishName) {
        return this.getMenu().getDishByName(dishName);
    }

    /**
     * Finds and returns a meal from the menu by its name.
     *
     * @param mealName the name of the meal to search for.
     * @return the {@link Meal} object if found, otherwise {@code null}.
     */
    public Meal getMealByName(String mealName) {
        return this.getMenu().getMealByName(mealName);
    }

    /**
     * Returns a string representation of the Restaurant.
     * The format is "Restaurant [name] [location]".
     *
     * @return a {@link String} describing the restaurant.
     */
    @Override
    public String toString() {
        return "Restaurant " + name + " " + location.toString();
    }

    /**
     * Gets the total number of orders processed by the restaurant.
     *
     * @return the total order count.
     */
    public int getOrderCounter() {
        return orderCounter;
    }

    /**
     * Sets the order counter to a specific value.
     *
     * @param orderCounter the new value for the order counter.
     */
    public void setOrderCounter(int orderCounter) {
        this.orderCounter = orderCounter;
    }

    /**
     * Increments the order counter by one.
     * This is typically called each time a new order is completed.
     */
    public void incrementOrderCounter() {
        this.orderCounter++;
    }
}