package user;

import java.util.ArrayList;

import order.Order;
import system.AvailableCourierNotFoundException;

/**
 * Class representing a Courier in the system.
 * A Courier is a type of user who can deliver orders.
 * It extends the Person class and includes additional attributes
 * such as position, phone number, delivery counter, and duty status.
 * 
 * @author Aymane Adib
 */
public class Courier extends Person{
    
    private Location position; // The current location of the courier
    private String phoneNumber; // The phone number of the courier
    private int deliveryCounter; // The number of deliveries made by the courier
    private boolean onDuty; // Indicates if the courier is currently on duty
    private ArrayList<Order> pendingOrders;
	private Order currentDeliveringOrder;

	/**
     * Constructor for Courier.
     * Initializes the courier with the provided details.  
     * @param name name of the courier,
     * @param surname surname of the courier, 
     * @param username username of the courier,
     * @param password password of the courier,
     * @param phoneNumber phone number of the courier,
     * @param position current location of the courier.
     * @throws BadUserCreationException if the phone number is already used by another account.
     */
    public Courier(String name, String surname, String username, String password, String phoneNumber, Location position) throws BadUserCreationException {
        super(name, surname,username, password);
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.deliveryCounter = 0;
        this.onDuty = false;
        phonesUsed.add(phoneNumber);
        this.pendingOrders = new ArrayList<Order>();
        this.currentDeliveringOrder = null;
    }
    
    /**
     * Returns the current position of the courier.     
     * @return the location of the courier.
     */
    public Location getPosition() {
        return position;
    }

    /**
     * Sets the position of the courier.
     * @param position the new location of the courier.
     */
    public void setPosition(Location position) {
        this.position = position;
    }

    /**
     * Returns the phone number of the courier.
     * @return the phone number of the courier.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the courier.
     * @param phoneNumber the new phone number of the courier.
     * @throws BadUserCreationException if the phone number is already used by another account.
     */
    public void setPhoneNumber(String phoneNumber) throws BadUserCreationException {
        if (phonesUsed.contains(phoneNumber)) {
            throw new BadUserCreationException("Phone number already used by another account: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the number of deliveries made by the courier.
     * @return the delivery counter of the courier.
     */
    public int getDeliveryCounter() {
        return deliveryCounter;
    }

    /**
     * Sets the delivery counter of the courier.
     * @param deliveryCounter the new delivery counter of the courier.
     */
    public void setDeliveryCounter(int deliveryCounter) {
        this.deliveryCounter = deliveryCounter;
    }

    /**
     * Returns whether the courier is currently on duty.
     * @return true if the courier is on duty, false otherwise.
     */
    public boolean isOnDuty() {
        return onDuty;
    }

    /**
     * Sets the duty status of the courier.
     * @param onDuty true if the courier is on duty, false otherwise.
     */
    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    /**
     * Returns a string representation of the courier.
     * @return a string containing the name, surname, username, and delivery counter of the courier.
     */
    @Override
    public String toString() {
        return name + " " + surname + " ( " + username + " - Courrier with " + deliveryCounter + " deliveries )"; 
    }

    /**
     * Increments the delivery counter of the courier by 1.
     */
    public void incrementDeliveryCount() {
        this.deliveryCounter++;
    }
    
    /**
     * Adds an order to the pending orders.
     */
    public void addPendingOrder(Order order) {
    	this.pendingOrders.add(order);
    }
    
    /**
     * Gets the pending Orders.
     */
    public ArrayList<Order> getPendingOrders() {
		return this.pendingOrders;
	}
    
    /**
     * Accepts the order of id orderID
     * @param the orderID
     * @return true if the Order was indeed accepted
     */
    public boolean acceptOrder(int orderID) {
    	// Accepts the chosen Order
    	Order foundOrder = null;
    	for (Order order : pendingOrders) {
    		if (order.getId() == orderID) {
    			foundOrder = order;
    			break;
    		}
    	}
    	
    	if (foundOrder != null) {
    		// Accepts order
    		this.currentDeliveringOrder = foundOrder;
    		
    		// Sets this courier to the order
    		foundOrder.setCourier(this);
    		
    		// Clear the list of possible couriers of the order
    		foundOrder.setPossibleCouriers(new ArrayList<Courier>());
    		
    		// Changing order status
    		foundOrder.setCurrentStatus("ACCEPTED AND DELIVERING");
    		
    		// Removes this order of pending order
    		this.pendingOrders.remove(foundOrder);
    		
    		// This courier is not on duty anymore
    		this.setOnDuty(false);
        	
        	// Refuses all other orders
        	for (Order order : pendingOrders) {
        		refuseOrder(order);
        	}
        	
        	return true;
    	}
    	
    	return false;
    }
    
    /**
     * Refuses a order.
     * @param the order to be refused
     */
    public void refuseOrder(Order order) {
    	// Removing this courier of possibleCouriers of the order and notifies next
    	order.removeCourierFromPossibleCourier(this);
    	order.notifyNextCourier();
    	// Removing this order of pending orders
    	this.pendingOrders.remove(order);
    }
    
    /**
     * Refuses the order of id orderID
     * @param the orderID
     * @return return true if the order was indeed accepted
     */
    public boolean refuseOrder(int orderID) {
    	Order foundOrder = null;
    	for (Order order : pendingOrders) {
    		if (order.getId() == orderID) {
    			foundOrder = order;
    			break;
    		}
    	}
    	
    	if (foundOrder != null) {
    		// Removing this courier of possibleCouriers of the order and notifies next
        	foundOrder.removeCourierFromPossibleCourier(this);
        	foundOrder.notifyNextCourier();
        	// Removing this order of pending orders
    		this.pendingOrders.remove(foundOrder);
    		
    		return true;
    	}
    	
    	return false;
    }
}
