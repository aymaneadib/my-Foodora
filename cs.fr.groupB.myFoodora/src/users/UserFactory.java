package users;

/**
 * Exception class for handling errors related to user creation.
 * This exception is thrown when there are issues with creating a user.
 * 
 * @author Aymane Adib
 */
public class UserFactory {

    /**
     * Creates a user of the specified type with the given arguments.
     *
     * @param type the type of user to create (e.g., "manager", "restaurant", "courier", "customer")
     * @param args the arguments required for creating the user
     * @return the created User object
     * @throws BadUserCreationException if there is an error creating the user
     */
    public User createUser(String type, String... args) throws BadUserCreationException {
        switch (type.toLowerCase()) {
            case "manager":
                return createManager(args);
            case "restaurant":
                return createRestaurant(args);
            case "courier":
                return createCourier(args);
            case "customer":
                return createCustomer(args);
            default:
                throw new BadUserCreationException("Unknown user type: " + type);
        }
        
    }

    /**
     * Creates a manager with the given arguments.
     *
     * @param args the arguments required for creating the user
     * @return the created User object
     * @throws BadUserCreationException if there is an error creating the user
     */
    private Manager createManager(String... args) throws BadUserCreationException {
        if (args.length != 4) {
            throw new BadUserCreationException("Creating a Manager requires 4 arguments.");
        }
        return new Manager(args[0], args[1], args[2], args[3]);
    }

    /**
     * Creates a restaurant with the given arguments.
     *
     * @param args the arguments required for creating the restaurant
     * @return the created Restaurant object
     * @throws BadUserCreationException if there is an error creating the restaurant
     */
    private Restaurant createRestaurant(String... args) throws BadUserCreationException {
        if ( args.length != 5) {
            throw new BadUserCreationException("Creating a Restaurant requires 5 arguments.");
        }
        double x = Double.parseDouble(args[3]);
        double y = Double.parseDouble(args[4]);
        Location location = new Location(x, y);
        return new Restaurant(args[0], args[1], args[2], location);

    }

    /**
     * Creates a courier with the given arguments.
     *
     * @param args the arguments required for creating the courier
     * @return the created Courier object
     * @throws BadUserCreationException if there is an error creating the courier
     */
    private Courier createCourier(String... args) throws BadUserCreationException {
        if (args.length != 7) {
            throw new BadUserCreationException("Creating a Courier requires 7 arguments.");
        }
        double x = Double.parseDouble(args[5]);
        double y = Double.parseDouble(args[6]);
        Location location = new Location(x, y);
        return new Courier(args[0], args[1], args[2], args[3], args[4], location);
    }

    /**
     * Creates a customer with the given arguments.
     *
     * @param args the arguments required for creating the customer
     * @return the created Customer object
     * @throws BadUserCreationException if there is an error creating the customer
     */
    private Customer createCustomer(String... args) throws BadUserCreationException {
        if (args.length != 8 && args.length != 9) {
            throw new BadUserCreationException("Creating a Customer requires either 8 or 9 arguments.");
        }

        double x = Double.parseDouble(args[6]);
        double y = Double.parseDouble(args[7]);
        Location location = new Location(x, y);

        boolean consent = false;
        if (args.length == 9) {
            consent = Boolean.parseBoolean(args[8]);
        }
        return new Customer(args[0], args[1], args[2], args[3], args[4], args[5], location, consent);
    }

}
