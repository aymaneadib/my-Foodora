package users;

import food.*;
import system.MyFoodora;
import order.Order;

import java.util.*;;

public class Restaurant extends User {
    
    private static final List<String> availableOperations = new ArrayList<>(); 
    
    private Location location;
    private Menu menu;

    public Restaurant(String name, String username, String password, Location location) throws BadUserCreationException {
        super(name, username, password);
        this.location = location;
        this.menu = new Menu();
    }

    public Restaurant(String name, String username, String password, Location location, Menu menu) throws BadUserCreationException {
        super(name, username, password);
        this.location = location;
        this.menu = menu;
    }

    public ArrayList<Order> orderHistory(MyFoodora system){
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : system.getOrderHistory()) {
            if (order.getRestaurant().equals(this)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public void addDish(Dish dish) {
        menu.addDish(dish);
    }

    public void removeDish(Dish dish) {
        menu.removeDish(dish);
    }

    public void addMeal(Meal meal) {
        menu.addMeal(meal);
    }

    public void removeMeal(Meal meal) {
        menu.removeMeal(meal);
    }

    public void setGeneralDiscount(double discount) {
        menu.setGeneralDiscount(discount);
    }

    public void setSpecialDiscount(double discount) {
        menu.setSpecialDiscount(discount);
    }

    public static List<String> getAvailableoperations() {
        return availableOperations;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant "+ name +" "+ location.toString();
    }

}