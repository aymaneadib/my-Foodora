package user;

import food.*;
import system.MyFoodora;
import order.Order;
import fidelity.*;

import java.util.*;

/**
 * The Restaurant class represents a restaurant in the MyFoodora system.
 * It extends the User class and provides methods for managing the restaurant's menu and orders.
 * 
 * @author Aymane Adib
 */
public class Restaurant extends User {
    
    private static final List<String> availableOperations = new ArrayList<>(); // List of available operations for the restaurant
    
    private Location location;
    private Menu menu;
    private int orderCounter;

    /**
     * Constructor for the Restaurant class.
     *
     * @param name     the name of the restaurant
     * @param username the username of the restaurant
     * @param password the password of the restaurant
     * @param location the location of the restaurant
     * @throws BadUserCreationException if there is an error creating the user
     */
    public Restaurant(String name, String username, String password, Location location) throws BadUserCreationException {
        super(name, username, password);
        this.location = location;
        this.menu = new Menu();
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public ArrayList<Order> orderHistory(MyFoodora system){
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : system.getOrderHistory()) {
            if (order.getRestaurant().equals(this)) {
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public double getPrice(Order order, Customer customer) {
        FidelityCard card = customer.getFidelityCard();
        return card.getFinalPrice(order);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void addDish(Dish dish) {
        menu.addDish(dish);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void removeDish(Dish dish) {
        menu.removeDish(dish);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void addMeal(Meal meal) {
        menu.addMeal(meal);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void removeMeal(Meal meal) {
        menu.removeMeal(meal);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void setGeneralDiscount(double discount) {
        menu.setGeneralDiscount(discount);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void setSpecialDiscount(double discount) {
        menu.setSpecialDiscount(discount);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public double getGeneralDiscount() {
        return menu.getGeneralDiscount();
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public double getSpecialDiscount() {
        return menu.getSpecialDiscount();
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public static List<String> getAvailableoperations() {
        return availableOperations;
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public Location getLocation() {
        return location;
    } 

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * Returns a dish by its name from a specific restaurant's menu.
     * 
     * @param dishName
     * @return
     */
    public Dish getDishByName(String dishName) {
        // Search for the dish in the restaurant's menu
        return this.getMenu().getDishByName(dishName);
        
    }

    /**
     * Returns a meal by its name from a specific restaurant's menu.
     * 
     * @param mealName
     * @return
     */
    public Meal getMealByName(String mealName) {
        // Search for the meal in the restaurant's menu
        return this.getMenu().getMealByName(mealName);
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    @Override
    public String toString() {
        return "Restaurant "+ name +" "+ location.toString();
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public int getOrderCounter() {
        return orderCounter;
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void setOrderCounter(int orderCounter) {
        this.orderCounter = orderCounter;
    }

    /**
     * Returns the list of available operations for the restaurant.
     *
     * @return the list of available operations
     */
    public void incrementOrderCounter() {
        this.orderCounter++;
    }

}