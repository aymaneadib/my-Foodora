package users;

import java.util.*;

import fidelity.*;
import order.*;
import system.*;

public class Customer extends Person {

    private static Set<String> emailsUsed = new HashSet<>();
    private static Set<String> availableOperations = new HashSet<>();
    
    private Location adress;
    private String phoneNumber;
    private FidelityCard fidelityCard;
    private boolean notificationsConsent;
    private String email;

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
    }

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
    }

    public Location getAdress() {
        return adress;
    }

    public void setAdress(Location adress) {
        this.adress = adress;
    }

    public static Set<String> getAvailableOperations() {
        return availableOperations;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) throws BadUserCreationException {
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }

    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

    public boolean isNotificationsConsent() {
        return notificationsConsent;
    }

    public void setNotificationsConsent(boolean notificationsConsent) {
        this.notificationsConsent = notificationsConsent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws BadUserCreationException {
        if (emailsUsed.contains(email)) {
            throw new BadUserCreationException("Email already used by another account: " + email);
        }
        this.email = email;
    }

    // public Order placeOrder(){
    //     return null;
    // }

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

    @Override
    public String toString() {
        return name + " " + surname + " <"+ email+"> ( " + username + " - Customer )"; 
    }

}
