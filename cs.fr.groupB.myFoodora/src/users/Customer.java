package users;

import java.util.*;

import fidelity.*;
import order.*;
import food.*;
import system.*;

/**
 * Class representing a Customer in the MyFoodora system.
 * It extends the Person class and implements the User interface.
 * 
 * @author Aymane Adib
 */
public class Customer extends Person implements notifications.Observer {

    private static Set<String> emailsUsed = new HashSet<>(); // Set to store used emails
    private static final Set<String> availableOperations = new HashSet<>(); // Set to store available operations
    
    private Location adress;
    private String phoneNumber;
    private FidelityCard fidelityCard;
    private boolean notificationsConsent;
    private String email;
    private String notifications; // Set to store used phone numbers

    /**
     * Constructor for Customer.
     * Initializes the customer with the provided details.
     * 
     * @param name        name of the customer
     * @param surname     surname of the customer
     * @param username    username of the customer
     * @param password    password of the customer
     * @param phoneNumber phone number of the customer
     * @param email       email of the customer
     * @param adress      address of the customer
     * @throws BadUserCreationException if the email or phone number is already used by another account
     */
    public Customer(String name, String surname, String username, String password, String phoneNumber, String email, Location adress) throws BadUserCreationException {
        super(name, surname, username, password);
        if (emailsUsed.contains(email)) {
            throw new BadUserCreationException("Email already used by another account: " + email);
        }
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.fidelityCard = new BasicCard(this);
        this.notificationsConsent = false;
        this.email = email;
        this.notifications = "";
    }

    /**
     * Constructor for Customer with consent.
     * Initializes the customer with the provided details and consent for notifications.
     * 
     * @param name        name of the customer
     * @param surname     surname of the customer
     * @param username    username of the customer
     * @param password    password of the customer
     * @param phoneNumber phone number of the customer
     * @param email       email of the customer
     * @param adress      address of the customer
     * @param consent     consent for notifications
     * @throws BadUserCreationException if the email or phone number is already used by another account
     */
    public Customer(String name, String surname, String username, String password, String phoneNumber, String email, Location adress, Boolean consent) throws BadUserCreationException {
        super(name, surname, username, password);
        if (emailsUsed.contains(email)) {
            throw new BadUserCreationException("Email already used by another account: " + email);
        }
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.fidelityCard = new BasicCard(this);
        this.notificationsConsent = consent;
        this.email = email;
        this.notifications = "";
    }

    /**
     * Notifies the customer about the new meal of the week.
     * 
     * @param meal the meal to observe
     */
    @Override
    public void update(Meal mealOfTheWeek) {
        // Notify the customer about the new meal of the week
        if (notificationsConsent) {
            if (notifications != "") {
                 notifications += "\n";
            }
            notifications += mealOfTheWeek.toString() + " is the new meal of the week !! ";
        }
    }

    /**
     * Returns the address of the customer.
     * 
     * @return the address of the customer
     */
    public Location getAdress() {
        return adress;
    }

    /**
     * Sets the address of the customer.
     * 
     * @param adress the new address of the customer
     */
    public void setAdress(Location adress) {
        this.adress = adress;
    }

    /**
     * Returns the set of used emails.
     * 
     * @return
     */
    public static Set<String> getAvailableoperations() {
        return availableOperations;
    }

    /**
     * Returns the available operations for the customer.
     * 
     * @return the set of available operations
     */
    public static Set<String> getAvailableOperations() {
        return availableOperations;
    }

    /**
     * Returns the phone number of the customer.
     * 
     * @return the phone number of the customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the customer.
     * 
     * @param phoneNumber the new phone number of the customer
     * @throws BadUserCreationException if the phone number is already used by another account
     */
    public void setPhoneNumber(String phoneNumber) throws BadUserCreationException {
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the fidelity card of the customer.
     * 
     * @return the fidelity card of the customer
     */
    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }

    /**
     * Sets the fidelity card of the customer.
     * 
     * @param fidelityCard the new fidelity card of the customer
     */
    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

    /**
     * Returns the consent for notifications.
     * 
     * @return true if consent is given, false otherwise
     */
    public boolean isNotificationsConsent() {
        return notificationsConsent;
    }

    /**
     * Sets the consent for notifications.
     * 
     * @param notificationsConsent the new consent for notifications
     */
    public void setNotificationsConsent(boolean notificationsConsent) {
        this.notificationsConsent = notificationsConsent;
        if (notificationsConsent) {
            Meal.registerObserver(this);
        }
    }

    /**
     * Returns the email of the customer.
     * 
     * @return the email of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the customer.
     * 
     * @param email the new email of the customer
     * @throws BadUserCreationException if the email is already used by another account
     */
    public void setEmail(String email) throws BadUserCreationException {
        if (emailsUsed.contains(email)) {
            throw new BadUserCreationException("Email already used by another account: " + email);
        }
        this.email = email;
    }

    // public Order placeOrder(){
    //     return null;
    // }

    /**
     * Returns the order history of the customer.
     * 
     * @param system the MyFoodora system
     * @return a set of orders made by the customer
     */
    public HashSet<Order> getHistory(MyFoodora system) {
        HashSet<Order> orderHistory = system.getOrderHistory();
        HashSet<Order> orders = new HashSet<>();
        for (Order order : orderHistory) {
            if (order.getCustomer().equals(this)) {
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Returns a string representation of the Customer object.
     * 
     * @return a string representing the customer
     */
    @Override
    public String toString() {
        return name + " " + surname + " <"+ email+"> ( " + username + " - Customer )"; 
    }

}
