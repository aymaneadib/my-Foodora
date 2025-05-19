package users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import order.Order;
import system.*;

/**
 * The Manager class represents a manager in the MyFoodora system.
 * It extends the Person class and provides methods for managing users and setting service fees.
 * 
 * @author Aymane Adib
 */
public class Manager extends Person {

    private static final List<String> availableOperations = new ArrayList<>(); // List of available operations for the manager

    /**
     * Constructor for the Manager class.
     *
     * @param name     the name of the manager
     * @param surname  the surname of the manager
     * @param username the username of the manager
     * @param password the password of the manager
     * @throws BadUserCreationException if there is an error creating the user
     */
    public Manager(String name, String surname, String username, String password) throws BadUserCreationException {
        super(name, surname, username, password);
    }

    /**
     * Static block to initialize the available operations for the manager.
     */
    public void addUser(User user, MyFoodora system) {
        system.addUser(user);
    }

    /**
     * Adds a user to the system.
     *
     * @param user   the user to be added
     * @param system the MyFoodora system
     */
    public void removeUser(User user, MyFoodora system) {
        system.removeUser(user);
    }

    /**
     * Sets the service fee for the system.
     *
     * @param fee    the service fee to be set
     * @param system the MyFoodora system
     */
    public void setServiceFee(double fee, MyFoodora system) {
        system.setServiceFee(fee);
    }

    /**
     * Sets the delivery cost for the system.
     * 
     * @param cost   the delivery cost to be set
     * @param system the MyFoodora system
     */
    public void setDeliveryCost(double cost, MyFoodora system) {
        system.setDeliveryCost(cost);
    }

    /**
     * Sets the markup percentage for the system.
     * 
     * @param percentage
     * @param system
     */
    public void setMarkupPercentage(double percentage, MyFoodora system) {
        system.setMarkupPercentage(percentage);
    }

    /**
     * Gets the list of orders within a specified date range.
     *
     * @param system    the MyFoodora system
     * @param startdate the start date of the range
     * @param enddate   the end date of the range
     * @return a list of orders within the specified date range
     */
    public ArrayList<Order> getOrders(MyFoodora system, LocalDate startdate, LocalDate enddate) {
        ArrayList<Order> orders = new ArrayList<Order>();
        for (Order order : system.getOrders()) {
            if (order.getDate().isAfter(startdate) && order.getDate().isBefore(enddate)) {
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Computes the total income for the system within a specified date range.
     * @param system
     * @param startdate
     * @param enddate
     * @return
     */
    public double computeTotalIncome(MyFoodora system, LocalDate startdate, LocalDate enddate) {
        double totalIncome = 0;
        for (Order order : getOrders(system, startdate, enddate)) {
            totalIncome += order.getPrice() + system.getProfitData().getServiceFee();
        }
        return totalIncome;
    }

    /**
     * Computes the total profit for the system within a specified date range.
     *
     * @param system    the MyFoodora system
     * @param startdate the start date of the range
     * @param enddate   the end date of the range
     * @return the total profit for the system within the specified date range
     */
    public double computeTotalProfit(MyFoodora system, LocalDate startdate, LocalDate enddate) {
        double totalProfit = 0;
        for (Order order : getOrders(system, startdate, enddate)) {
            totalProfit += order.getPrice() * system.getProfitData().getMarkupPercentage() + system.getProfitData().getServiceFee() - system.getProfitData().getDeliveryCost();
        }
        return totalProfit;
    }

    /**
     * Gets the list of active customers within a specified date range.
     * @param system
     * @param startdate
     * @param enddate
     * @return
     */
    public List<Customer> getActiveCustomers(MyFoodora system, LocalDate startdate, LocalDate enddate) {
        List<Customer> activeCustomers = new ArrayList<>();
        for (Customer customer : system.getCustomers()) {
            for (Order order : customer.getHistory(system)) {
                if (order.getDate().isAfter(startdate) && order.getDate().isBefore(enddate)) {
                    activeCustomers.add(customer);
                    break;
                }
            }
        }
        return activeCustomers;
    }

    /**
     * Computes the average profit per customer within a specified date range.
     *
     * @param system    the MyFoodora system
     * @param startdate the start date of the range
     * @param enddate   the end date of the range
     * @return the average profit per customer within the specified date range
     */
    public double computeAverageProfitPerCustomer(MyFoodora system, LocalDate startdate, LocalDate enddate) {
        double totalProfit = computeTotalProfit(system, startdate, enddate);
        double totalCustomers = getActiveCustomers(system,startdate,enddate).size();
        return totalProfit / totalCustomers;
    }

    /**
     * Gets the most selling restaurant in the system.
     * 
     * @param system the MyFoodora system
     * @return the most selling restaurant in the system
     */
    public Restaurant mostSelliRestaurant(MyFoodora system){
        ArrayList<Restaurant> restaurants = system.getRestaurants();
        RestaurantSorter restaurantSorter = new RestaurantSorter();
        restaurantSorter.sort(restaurants);
        return restaurants.get(0);
    }

    /**
     * Gets the least selling restaurant in the system.
     *
     * @param system the MyFoodora system
     * @return the least selling restaurant in the system
     */
    public Restaurant leastSelliRestaurant(MyFoodora system){
        ArrayList<Restaurant> restaurants = system.getRestaurants();
        RestaurantSorter restaurantSorter = new RestaurantSorter();
        restaurantSorter.sort(restaurants);
        return restaurants.get(restaurants.size()-1);
    }

    /**
     * Gets the most active courier in the system.
     * @param system the MyFoodora system
     * @return the most active courier in the system
     */
    public Courier mostActiveCourier(MyFoodora system){
        ArrayList<Courier> couriers = system.getCouriers();
        CourierSorter courierSorter = new CourierSorter();
        courierSorter.sort(couriers);
        return couriers.get(0);
    }

    /**
     * Gets the least active courier in the system.
     *
     * @param system the MyFoodora system
     * @return the least active courier in the system
     */
    public Courier leastActiveCourier(MyFoodora system){
        ArrayList<Courier> couriers = system.getCouriers();
        CourierSorter courierSorter = new CourierSorter();
        courierSorter.sort(couriers);
        return couriers.get(couriers.size()-1);
    }

    /**
     * Gets the list of available operations for the manager.
     *
     * @param operation the operation to be added
     */
    public static List<String> getAvailableOperations() {
        return availableOperations;
    }

    /**
     * Sets the delivery policy - to assign couriers to an order - in the system.
     * @param system the MyFoodora system
     * @param deliveryStrategy the delivery strategy to be set
     */
    public void setDeliveryStrategy(MyFoodora system, DeliveryStrategy deliveryStrategy) {
        system.setDeliveryStrategy(deliveryStrategy);
    }

    /**
     * Gets the profit data for the system based on the last month's orders and target profit.
     *
     * @param system        the MyFoodora system
     * @param profitData    the current profit data
     * @param lastMonthOrders the set of orders from the last month
     * @param targetProfit  the target profit to be achieved
     * @return the updated profit data
     */
    public ProfitData getProfitData(ProfitStrategy strategy,ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
        return strategy.getProfitData(profitData, lastMonthOrders, targetProfit);
    }

    /**
     * Adds an operation to the list of available operations for the manager.
     *
     * @param operation the operation to be added
     */
    @Override
    public String toString() {
        return "Manager " + surname + " ( "+ id +" )";
    }
}