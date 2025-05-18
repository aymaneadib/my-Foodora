package users;

public class UserFactory {

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

    private Manager createManager(String... args) throws BadUserCreationException {
        if (args.length != 4) {
            throw new BadUserCreationException("Creating a Manager requires 4 arguments.");
        }
        return new Manager(args[0], args[1], args[2], args[3]);
    }

    private Restaurant createRestaurant(String... args) throws BadUserCreationException {
        if ( args.length != 5) {
            throw new BadUserCreationException("Creating a Restaurant requires 5 arguments.");
        }
        double x = Double.parseDouble(args[3]);
        double y = Double.parseDouble(args[4]);
        Location location = new Location(x, y);
        return new Restaurant(args[0], args[1], args[2], location);

    }

    private Courier createCourier(String... args) throws BadUserCreationException {
        if (args.length != 7) {
            throw new BadUserCreationException("Creating a Courier requires 7 arguments.");
        }
        double x = Double.parseDouble(args[5]);
        double y = Double.parseDouble(args[6]);
        Location location = new Location(x, y);
        return new Courier(args[0], args[1], args[2], args[3], args[4], location);
    }

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
