package order;

import users.*;
import food.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Order {

    private static int orderCounter = 0;

    private int id;
    private Customer customer;
    private Restaurant restaurant;
    private Courier courier;
    private LocalTime time;
    private LocalDate date;
    private ArrayList<Dish> dishes;
    private ArrayList<Meal> meals;
    private double price;

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
    }

    public void addDish(Dish dish){
        
        dishes.add(dish);
        price += dish.getPrice();
    }

    public void removeDish(Dish dish) throws BadOrderHandlingException {
        if (dishes.remove(dish)) {
            price -= dish.getPrice();
        } else {
            throw new BadOrderHandlingException("Dish not found in the order.");    
        }
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
        price += meal.getPrice();
    }

    public void removeMeal(Meal meal) throws BadOrderHandlingException {
        if (meals.remove(meal)) {
            price -= meal.getPrice();
        }
        else {
            throw new BadOrderHandlingException("Meal not found in the order.");
        }
    }

    public Order(Customer customer, Restaurant restaurant, Courier courier) {
        this(customer, restaurant, courier, LocalTime.now(), LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order nº "+ id + " from " + restaurant.getName() + " to " + customer.getName();
    }


}
