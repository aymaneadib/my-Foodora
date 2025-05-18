package users;

import java.util.ArrayList;
import java.util.List;

import system.MyFoodora;
import system.UserNotFoundException;

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
    public void removeUser(User user, MyFoodora system) throws UserNotFoundException{
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
     * Adds an operation to the list of available operations for the manager.
     *
     * @param operation the operation to be added
     */
    public static List<String> getAvailableOperations() {
        return availableOperations;
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
