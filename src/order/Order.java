package order;

import food.*;
import system.AvailableCourierNotFoundException;
import user.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The Order class represents an order in the MyFoodora system.
 * It contains information about the customer, restaurant, courier, time, date, dishes, meals, and price.
 * 
 * @author Aymane Adib
 */
public class Order{

    private static int orderCounter = 0; // Counter for unique order IDs

    private int id;
    private Customer customer;
    private Restaurant restaurant;
    private Courier courier;
    private LocalTime time;
    private LocalDate date;
    private ArrayList<Dish> dishes;
    private ArrayList<Meal> meals;
    private double price;
    private String currentStatus;
    private ArrayList<Courier> possibleCouriers;

	/**
     * Constructor for the Order class.
     *
     * @param customer   the customer who placed the order
     * @param restaurant the restaurant from which the order is placed
     * @param courier    the courier assigned to deliver the order
     * @param time       the time of the order
     * @param date       the date of the order
     */
    public Order(Customer customer, Restaurant restaurant, Courier courier, LocalTime time, LocalDate date) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.courier = courier;
        this.time = time;
        this.date = date;
        this.dishes = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.price = 0.0;
        this.id = ++orderCounter;
        this.currentStatus = "WAINTING FOR COMPLETION";
        this.possibleCouriers = new ArrayList<Courier>();
    }

    /**
     * Constructor for the Order class with default time and date.
     *
     * @param customer   the customer who placed the order
     * @param restaurant the restaurant from which the order is placed
     * @param courier    the courier assigned to deliver the order
     */
    public Order(Customer customer, Restaurant restaurant, Courier courier) {
        this(customer, restaurant, courier, LocalTime.now(), LocalDate.now());
    }
    
    /**
     * Returns the list of possible couriers to deliver.
     *
     * @return the list of possible couriers
     */
    public ArrayList<Courier> getPossibleCouriers() {
		return possibleCouriers;
	}

    /**
     * Sets the list of possible couriers to deliver.
     *
     * @param possibleCouriers the list of possible couriers
     */
	public void setPossibleCouriers(ArrayList<Courier> possibleCouriers) {
		this.possibleCouriers = possibleCouriers;
	}
	
	/**
     * Removes a courier from the list of possible couriers.
     *
     * @param courier the courier to be removed
     */
	public void removeCourierFromPossibleCourier(Courier courier) {
		this.possibleCouriers.remove(courier);
	}
	
	/**
     * Notifies the next courier of the list.
     *
     */
	public void notifyNextCourier(){
		if (this.possibleCouriers.size() != 0) {
			this.possibleCouriers.get(0).addPendingOrder(this);
		}
	}
    
    /**
     * Returns the current status of the order.
     *
     * @return the status of the order
     */
    public String getCurrentStatus() {
    	return this.currentStatus;
    }
    
    /**
     * Sets the current status of the order.
     *
     * @param status the status of the order
     */
    public void setCurrentStatus(String status) {
    	this.currentStatus = status;
    }

        /**
     * Adds a dish to the order.
     * 
     * @param dish
     */
    public void addDish(Dish dish){
        dishes.add(dish);
        price += dish.getPrice();
        dish.incrementFrequencyDelivery();
    }

    /**
     * Removes a dish from the order.
     * 
     * @param dish
     * @throws BadOrderHandlingException if the dish is not found in the order
     */
    public void removeDish(Dish dish) throws BadOrderHandlingException {
        if (dishes.remove(dish)) {
            price -= dish.getPrice();
            dish.decrementFrequencyDelivery();
        } else {
            throw new BadOrderHandlingException("Dish not found in the order.");    
        }
    }

    /**
     * Adds a meal to the order.
     * 
     * @param meal
     */
    public void addMeal(Meal meal) {
        meals.add(meal);
        price += meal.getPrice();
        meal.incrementFrequencyDelivery();
    }

    /**
     * Removes a meal from the order.
     * 
     * @param meal
     * @throws BadOrderHandlingException if the meal is not found in the order
     */
    public void removeMeal(Meal meal) throws BadOrderHandlingException {
        if (meals.remove(meal)) {
            price -= meal.getPrice();
            meal.decrementFrequencyDelivery();
        }
        else {
            throw new BadOrderHandlingException("Meal not found in the order.");
        }
    }

    /**
     * Returns the ID of the order.
     *
     * @return the ID of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the customer who placed the order.
     *
     * @return the customer who placed the order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer who placed the order.
     *
     * @param customer the customer who placed the order
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the restaurant from which the order is placed.
     *
     * @return the restaurant from which the order is placed
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets the restaurant from which the order is placed.
     *
     * @param restaurant the restaurant from which the order is placed
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Returns the courier assigned to deliver the order.
     *
     * @return the courier assigned to deliver the order
     */
    public Courier getCourier() {
        return courier;
    }

    /**
     * Sets the courier assigned to deliver the order.
     *
     * @param courier the courier assigned to deliver the order
     */
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    /**
     * Returns the time of the order.
     *
     * @return the time of the order
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the order.
     *
     * @param time the time of the order
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns the date of the order.
     *
     * @return the date of the order
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the order.
     *
     * @param date the date of the order
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the list of dishes in the order.
     *
     * @return the list of dishes in the order
     */
    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    /**
     * Sets the list of dishes in the order.
     *
     * @param dishes the list of dishes in the order
     */
    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     * Returns the list of meals in the order.
     *
     * @return the list of meals in the order
     */
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    /**
     * Sets the list of meals in the order.
     *
     * @param meals the list of meals in the order
     */
    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    /**
     * Returns the price of the order.
     *
     * @return the price of the order
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the price of the order.
     *
     * @param price the price of the order
     */
    public void setPrice(double price) {
    	this.price = price;
    }

    /**
     * String representation of the Order object.
     * 
     * @return a string representation of the Order object
     */
    @Override
    public String toString() {
        return "Order ID "+ id + " from " + restaurant.getName() + " to " + customer.getName();
    }

}
