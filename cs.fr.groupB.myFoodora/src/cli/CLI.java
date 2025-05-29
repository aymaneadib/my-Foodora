package cli;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import system.MyFoodora;
import user.*;
import food.*;
import fidelity.*;
import notification.*;
import order.*;
import system.*;


/**
 * Command Line Interface (CLI) for interacting with the MyFoodora system.
 * This class provides a text-based interface for users to perform various operations
 * such as logging in, registering, and managing orders.
 * 
 * @author Aymane Adib
 * @author Alisson Bonatto
 */
public class CLI {

    private static MyFoodora system;
// ---------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------
    /**
     * Main method to start the CLI application.
     * It initializes the MyFoodora system and provides a welcome message.
     * Users can enter commands to interact with the system.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize the MyFoodora system instance
        system = MyFoodora.getInstance();
        initialization();

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // Display welcome message and instructions
        welcomeMessage();

        // Start the command loop
        while (true) {
            // Prompt the user for input
            System.out.print("> ");
            input = scanner.nextLine().trim();

            // Check for exit command
            if (input.equalsIgnoreCase("exit")) {
                exit();
                break;
            }

            // Check for empty input
            if (input.isEmpty()) {
                continue;
            }

            // Handle the command input
            handleCommand(input);
        }

        // Close the scanner to release resources
        scanner.close();
    }
// ---------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------

    /**
     * Initializes the MyFoodora system and performs any necessary setup.
     * This method can be extended to include additional initialization logic.
     */
    public static void initialization(){
        //Do smth
    }

    /**
     * Displays a welcome message and instructions for using the CLI.
     * This method provides an overview of available commands and how to interact with the system.
     */
    public static void welcomeMessage(){
        System.out.println(
            "  _  _  _  _  ____  __    __  ____   __  ____   __  \n" +
            " ( \\/ )( \\/ )(  __)/  \\  /  \\(    \\ /  \\(  _ \\ / _\\ \n" +
            " / \\/ \\ )  (  ) _)(  O )(  O )) D ((  O ))   //    \\\n" +
            " \\_)(_/(__/  (__)  \\__/  \\__/(___/ \\__/(__\\_)\\_/\\__/\n" +
            "\n              Deliciously Delivered"
            + "\n"+
            "\n             ! Welcome to myFoodora ! \n"
        );
        System.out.println("You can interact with the App through this Command Line Interface");
        System.out.println("Available commands:");
        System.out.println("  HELP - Shows the  full help message");
        System.out.println("  LOGIN <username> <password>- Login to an existing user account");
        System.out.println("  REGISTER <userType> - Register your new account with the necessary information");
        System.out.println("  EXIT - Exit myFoodora...");
        System.out.println("Enter your commands below:");
    }

    /**
     * Handles user commands entered in the CLI.
     * It parses the input, identifies the command, and calls the appropriate method to execute it.
     *
     * @param input the command input from the user
     */
    private static void handleCommand(String input) {
        // Split input into command and arguments
        String[] parts = input.split("\\s+");
        String command = parts[0].toUpperCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        
        switch (command) {
            case "HELP":
                printHelp(system.getCurrentUser());
                break;
            case "LOGIN":
                login(args);
                break;
            case "REGISTER":
                register(args);
                break;
            case "LOGOUT":
                logout();
                break;
            case "REGISTERRESTAURANT":
                registerRestaurant(args);
                break;
            case "REGISTERCUSTOMER":
                registerCustomer(args);
                break;
            case "REGISTERCOURIER":
                registerCourier(args);
                break;
            case "CREATEDISH":
                createDish(args);
                break;
            case "CREATEMEAL":
                createMeal(args);
                break;
            case "REMOVEMEAL":
                removeMeal(args);
                break;
            case "REMOVEDISH":
                removeDish(args);
                break;
            case "SHOWMEAL":
                showMeal(args);
                break;
            case "SHOWDISH":
                showDish(args);
                break;
            case "SETSPECIALOFFER":
                setSpecialOffer(args);
                break;
            case "REMOVEFROMSPECIALOFFER":
                removeFromSpecialOffer(args);
                break;
            case "SETPRICE":
                setPrice(args);
                break;
            case "CREATEORDER":
                createOrder(args);
                break;
            case "ADDITEM2ORDER":
                addItem2Order(args);
                break;
            case "ENDORDER":
                endOrder(args);
                break;
            case "ONDUTY":
                onDuty();
                break;
            case "OFFDUTY":
                offDuty(args);
                break;
            case "SETDELIVERYPOLICY":
                setDeliveryPolicy(args);
                break;
            case "SETPROFITPOLICY":
                setProfitPolicy(args);
                break;
            case "ASSOCIATECARD":
                associateCard(args);
                break;
            case "SHOWORDERS":
                showOrders(args);
                break;
            case "SHOWRESTAURANTS":
                showRestaurants();
                break;
            case "SHOWPOPULARRESTAURANTS":
                showPopularRestaurants();
                break;
            case "SHOWMONEYSPENT":
                showMoneySpent(args);
                break;
            case "CHANGEADDRESS":
                changeAddress(args);
                break;
            case "CHANGEPHONENUMBER":
                changePhoneNumber(args);
                break;
            case "CONSENTNOTIFICATIONS":
                consentNotifications(args);
                break;
            case "SHOWCOURIERDELIVERIES":
                showCourierDeliveries();
                break;
            case "SHOWRESTAURANTTOP":
                showRestaurantTop();
                break;
            case "SHOWCUSTOMERS":
                showCustomers();
                break;
            case "SHOWMENUITEMS":
                showMenuItems(args);
                break;
            case "SHOWTOTALPROFIT":
                showTotalProfit(args);
                break;
            case "RUNTEST":
                runTest(args);
                break;
            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Type HELP for available commands");
        }
    }
    
    /**
     * Prints the help message based on the type of user currently logged in.
     * It displays generic commands and user-specific commands based on the user's role.
     *
     * @param user the current user logged into the system
     */
    private static void printHelp(User user) {
        printGenericHelp();
        if (user != null) {
            if (user.getClass()== Customer.class){
                printCustomerHelp();
            } else if (user.getClass() == Restaurant.class) {
                printRestaurantHelp();
                // Add restaurant-specific commands here
            } else if (user.getClass() == Courier.class) {
                printCourierHelp();
                // Add courier-specific commands here
            } else if (user.getClass() == Manager.class) {
                printManagerHelp();
            }
        }
    }

    /**
     * Prints the generic help message with available commands.
     * This method provides an overview of commands that can be used by any user type.
     */
    public static void printGenericHelp(){
	    System.out.println("----------------------------------------");
	    System.out.println("\nGeneric Commands Available :");
        System.out.println("    - HELP - Show this help message");
	    System.out.println("    - RUNTEST <testScenarioFile> - execute the list of CLUI commands contained in the testScenario file passed as argument");
	    System.out.println("    - LOGIN <username> <password> - Log in with the specified username and password.");
        System.out.println("    - REGISTER <userType> - Register a new user account. User types can be: CUSTOMER, RESTAURANT, COURIER, MANAGER.");
	    System.out.println("    - LOGOUT - Log out of the current session.");
	    System.out.println("    - EXIT - Exit myFoodora... \n");
        System.out.println("----------------------------------------\n");
    }

    /**
     * Prints the help message for the Courier user type.
     * This method can be extended to include specific commands and instructions for couriers.
     */
    public static void printCourierHelp(){
        System.out.println("Courier Commands Available :");
        System.out.println("    - ONDUTY - Mark yourself as on duty to accept orders.");
        System.out.println("    - OFFDUTY - Mark yourself as off duty to stop accepting new orders.");    
    }

    /**
     * Prints the help message for the Restaurant user type.
     * This method can be extended to include specific commands and instructions for restaurants.
     */
    public static void printRestaurantHelp(){
        System.out.println("Restaurant Commands Available :");
        System.out.println("    - CREATEDISH <dishType> <dishName> <unitPrice> <isVege [y/n]> <glutenFree [y/n]> - Add a dish to the restaurant's menu.");
        System.out.println("    - CREATEMEAL <mealType> <mealName> <dish1Name> <dish2Name> [<dish3Name>] - Create a meal with specified dishes and add it to your menu.");
        System.out.println("    - SHOWMEAL <mealName> - Show details of a specific meal from your menu.");
        System.out.println("    - SHOWDISH <dishName> - Show details of a specific dish from your menu.");
        System.out.println("    - REMOVEMEAL <mealName> - Remove a specific meal from your menu.");
        System.out.println("    - REMOVEDISH <dishName> - Remove a specific dish from your menu.");
        System.out.println("    - SETSPECIALOFFER <mealName> - Set a special offer for a meal.");
        System.out.println("    - REMOVEFROMSPECIALOFFER <mealName> - Remove a meal from the special offer.");
        System.out.println("    - SHOWMENUITEMS - Show details of your menu.");
        System.out.println("    - SETPRICE <dishName> <newPrice> - Changing the price of an existing dish in your menu.");
    }

    /**
     * Prints the help message for the Customer user type.
     * This method can be extended to include specific commands and instructions for customers.
     */
    public static void printCustomerHelp(){
        System.out.println("Customer Commands Available :");
        System.out.println("    - CREATEORDER <restaurantName> - Create a new order with the specified restaurant.");
        System.out.println("    - ADDITEM2ORDER <itemType> <itemName> - Add an item to an existing order.");
        System.out.println("    - ENDORDER - End the current order, finalizing it and processing it.");
        System.out.println("    - ASSOCIATECARD <cardNumber> - Associate a fidelity card with your account.");
        System.out.println("    - SHOWMENUITEMS <restaurantName> - Show details of a specific restaurant's menu.");
        System.out.println("    - SHOWRESTAURANTS - Show a list of all available restaurants.");
        System.out.println("    - SHOWORDERS [<restaurantName>] - Show a list of your past orders, can be to a specific restaurant.");
        System.out.println("    - SHOWPOPULARRESTAURANTS - Show a list of the most popular restaurants.");
        System.out.println("    - SHOWMONEYSPENT [<restaurantName>] - Show the total amount of money spent on orders, can be to a specific restaurant.");
        System.out.println("    - CHANGEADDRESS <x> <y>  - Change your delivery address.");
        System.out.println("    - CHANGEPHONENUMBER <newPhoneNumber> - Change your phone number.");
        System.out.println("    - CONSENTNOTIFICATIONS <yes/no> - Set your consent for receiving notifications.");
    }

    /**
     * Prints the help message for the Manager user type.
     * This method can be extended to include specific commands and instructions for managers.
     */
    public static void printManagerHelp(){
        System.out.println("Manager Commands Available :");
        System.out.println("    - SHOWMENUITEMS <restaurantName> - Show details of a specific restaurant's menu.");
        System.out.println("    - ASSOCIATECARD <cardNumber> <customerUsername> - Associate a fidelity card with a customer account.");
    }

    /**
     * Prints a message to the console, prefixed with the current user's username if available.
     * This method is used for displaying messages in a user-friendly format.
     *
     * @param message the message to be printed
     */
    public static void print(String message) {
        if (system.getCurrentUser() != null) {
            System.out.println("(" + system.getCurrentUser().getUsername() + ") " + message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Exits the CLI application.
     */
    public static void exit() {
        System.out.println("Exiting myFoodora... Goodbye!");
        // Perform the necessary cleanup before exiting
    }

    /**
     * Handles user login by processing the provided username and password.
     * This method can be extended to include authentication logic.
     *
     * @param args the username and password for login
     */
    public static void login(String... args) {
        // Do smth
    }

    /**
     * Registers a new user account based on the provided arguments.
     *
     * @param args the arguments for registration, such as user type and details
     */
    public static void register(String... args) {
        // Do smth
    }

    /**
     * Logs out the current user from the system.
     */
    public static void logout() {
        // Do smth
    }

    /**
     * Permits a MANAGER to register a new restaurant with the provided details.
     *
     * @param args the arguments for registering a restaurant
     */
    public static void registerRestaurant(String... args) {
        // Do smth
    }

    /**
     * Permits a MANAGER to registers a new customer with the provided details.
     *
     * @param args the arguments for registering a customer
     */
    public static void registerCustomer(String... args) {
        // Do smth
    }

    /**
     * Permits a MANAGER to register a new courier with the provided details.
     *
     * @param args the arguments for registering a courier
     */
    public static void registerCourier(String... args) {
        // Do smth
    }

    /**
     * Adds a dish to the restaurant's menu.
     *
     * @param args the arguments for adding a dish, such as dish name, price, and description
     */
    public static void createDish(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to add a dish to a menu.");
            return;
        }
        if (args.length == 5) {
            String dishName = args[1];
            String dishCategory = args[0];
            boolean glutenFree;
            boolean isVege;
            double unitPrice;

            // Parse glutenFree
            String glutenFreeArg = args[4].toLowerCase();
            if (glutenFreeArg.equals("yes") || glutenFreeArg.equals("true") || glutenFreeArg.equals("1") || glutenFreeArg.equals("y")) {
                glutenFree = true;
            } else if (glutenFreeArg.equals("no") || glutenFreeArg.equals("false") || glutenFreeArg.equals("0") || glutenFreeArg.equals("n")) {
                glutenFree = false;
            } else {
                print("Error: glutenFree must be yes/no, true/false, 1/0, y/n.");
                return;
            }

            // Parse isVege
            String isVegeArg = args[3].toLowerCase();
            if (isVegeArg.equals("yes") || isVegeArg.equals("true") || isVegeArg.equals("1") || isVegeArg.equals("y")) {
                isVege = true;
            } else if (isVegeArg.equals("no") || isVegeArg.equals("false") || isVegeArg.equals("0") || isVegeArg.equals("n")) {
                isVege = false;
            } else {
                print("Error: glutenFree must be yes/no, true/false, 1/0, y/n.");
                return;
            }


            // Parse unitPrice
            try {
                unitPrice = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                print("Error: unitPrice must be a valid number.");
                return;
            }

            try {
                Dish newDish = system.createDish( dishCategory, dishName,unitPrice, isVege,glutenFree);
                Restaurant restaurant = (Restaurant) system.getCurrentUser();
                restaurant.addDish(newDish);
                print("Dish added successfully to the Menu");
            } catch (Exception e) {
                print("Fail to add Dish to Menu! " + e.getMessage());
            }
        } else {
            print("Usage : addDishRestaurantMenu <dishType> <dishName> <unitPrice> <isVege [y/n]> <glutenFree [y/n]> ");
        }
    }

    /**
     * Creates a new meal with the provided details.
     *
     * @param args the arguments for creating a meal, such as meal name and description
     */
    public static void createMeal(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to create a meal.");
            return;
        }
        String mealType = args[0].toUpperCase();
        Restaurant restaurant = (Restaurant) system.getCurrentUser();
        switch (mealType){
            case "FULLMEAL":
                if (args.length == 5){
                    String mealName = args[1];
                    String dish1Name = args[2];
                    String dish2Name = args[3];
                    String dish3Name = args[4];

                    try {
                        Dish dish1 = restaurant.getDishByName(dish1Name);
                        Dish dish2 = restaurant.getDishByName(dish2Name);
                        Dish dish3 = restaurant.getDishByName(dish3Name);

                        if (dish1 == null || dish2 == null || dish3 == null) {
                            print("One or more dishes not found in the menu.");
                            return;
                        }

                        FullMeal meal = new FullMeal(mealName, Set.of(dish1, dish2, dish3));
                        restaurant.addMeal(meal);
                        print("Full meal created successfully.");
                    } catch (Exception e) {
                        print("Failed to create full meal: " + e.getMessage());
                    }
                } else {
                    print("Usage: createMeal FULLMEAL <mealName> <dish1Name> <dish2Name> <dish3Name>");
                }
                break;
            case "HALFMEAL":
                if (args.length == 4){
                    String mealName = args[1];
                    String dish1Name = args[2];
                    String dish2Name = args[3];

                    try {
                        Dish dish1 = restaurant.getDishByName(dish1Name);
                        Dish dish2 = restaurant.getDishByName(dish2Name);

                        if (dish1 == null || dish2 == null) {
                            print("One or more dishes not found in the menu.");
                            return;
                        }

                        HalfMeal meal = new HalfMeal(mealName, Set.of(dish1, dish2));
                        restaurant.addMeal(meal);
                        print("Half meal created successfully.");
                    } catch (Exception e) {
                        print("Failed to create half meal: " + e.getMessage());
                    }
                } else {
                    print("Usage: createMeal HALFMEAL <mealName> <dish1Name> <dish2Name>");
                }
                break;
            default:
                print("Error: Unrecognized meal type. Use FULLMEAL or HALFMEAL.");
                print("Usage: createMeal <mealType> <mealName> <dish1Name> <dish2Name> [<dish3Name>]");
                break;
        }
    }

    /**
     * Removes a meal from the restaurant's menu.
     *
     * @param args the arguments for removing a meal, such as meal name
     */
    public static void removeMeal(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to remove a meal from a menu.");
            return;
        }

        if (args.length == 1) {
            String mealName = args[0];
            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            Meal meal = restaurant.getMealByName(mealName);
            if (meal != null) {
                restaurant.removeMeal(meal);
                print("Meal removed successfully from your menu.");
            } else {
                print("Meal not found in your menu.");
            }
        } else {
            print("Usage: removeMeal <mealName>");
        }
    }

    /**
     * Removes a dish from the restaurant's menu.
     *
     * @param args the arguments for removing a dish, such as dish name
     */
    public static void removeDish(String... args){
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to remove a dish from a menu.");
            return;
        }

        if (args.length == 1) {
            String dishName = args[0];
            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            Dish dish = restaurant.getDishByName(dishName);
            if (dish != null) {
                restaurant.removeDish(dish);
                print("Dish removed successfully from your menu.");
            } else {
                print("Dish not found in your menu.");
            }
        } else {
            print("Usage: removeDish <dishName>");
        }
    }

    /**
     * Displays the details of a specific meal.
     *
     * @param args the arguments for showing a meal, such as meal ID
     */
    public static void showMeal(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to show a meal.");
            return;
        }

        if (args.length == 1) {
            String mealName = args[0];
            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            Meal meal = restaurant.getMealByName(mealName);
            if (meal != null) {
                print("Meal Details: " + meal.toString());
            } else {
                print("Meal not found in your menu.");
            }
        } else {
            print("Usage: showMeal <mealName>");
        }
    }

    /**
     * Displays the details of a specific dish.
     *
     * @param args the arguments for showing a dish, such as dish ID
     */
    public static void showDish(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to show a dish.");
            return;
        }

        if (args.length == 1) {
            String dishName = args[0];
            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            Dish dish = restaurant.getDishByName(dishName);
            if (dish != null) {
                print("Dish Details: " + dish.toString());
            } else {
                print("Dish not found in your menu.");
            }
        } else {
            print("Usage: showDish <dishName>");
        }
    }

    /**
     * Sets a special offer for a meal or dish.
     *
     * @param args the arguments for setting a special offer, such as meal ID and offer details
     */
    public static void setSpecialOffer(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to set a special offer.");
            return;
        }
        if (args.length == 1) {
            Meal meal = ((Restaurant) system.getCurrentUser()).getMealByName(args[0]);
            if (meal != null){
                meal.setPricingStrategy(new MealOfTheWeekDiscount());
                print("Special offer set for meal: " + meal.getName());
            } else {
                print("Meal not found in your menu.");
                return;
            }
        } else {
            print("Usage: setSpecialOffer <mealName>");
        }
    }

    /**
     * Removes a meal or dish from the special offer.
     *
     * @param args the arguments for removing from special offer, such as meal ID or dish ID
     */
    public static void removeFromSpecialOffer(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to remove from special offer.");
            return;
        }
        if (args.length == 1) {
            Meal meal = ((Restaurant) system.getCurrentUser()).getMealByName(args[0]);
            if (meal != null){
                meal.setPricingStrategy(new GeneralDiscountMeal());
                print("Removed special offer for meal: " + meal.getName());
            } else {
                print("Meal not found in your menu.");
                return;
            }
        } else {
            print("Usage: removeFromSpecialOffer <mealName>");
        }
    }

    /**
     * Creates a new order with the provided details.
     *
     * @param args the arguments for creating an order, such as customer ID and meal ID
     */
    public static void createOrder(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to create an order.");
            return;
        }
        if (((Customer)system.getCurrentUser()).getCurrentOrder() != null) {
            print("You already have an active order. Please complete it before creating a new one.");
            return;
        }
        if (args.length == 1){
            String restaurantName = args[0];

            Customer customer = (Customer) system.getCurrentUser();
            Restaurant restaurant = system.getRestaurantByName(restaurantName);
            if (restaurant == null) {
                print("Restaurant not found: " + restaurantName);
                return;
            }
            try {
                Order order = system.createOrder(restaurant, customer);
                print("Order created successfully with ID: " + order.getId());
            } catch (Exception e) {
                print("Failed to create order: " + e.getMessage());
            }
        } else {
            print("Usage: createOrder <restaurantName>");
            return;
        }
    }

    public static void setPrice(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to set the price of a dish.");
            return;
        }
        if (args.length == 2) {
            String dishName = args[0];
            double newPrice;

            try {
                newPrice = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                print("Error: Price must be a valid number.");
                return;
            }

            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            Dish dish = restaurant.getDishByName(dishName);
            if (dish != null) {
                dish.setPrice(newPrice);
                print("Price updated successfully for dish: " + dish.getName());
            } else {
                print("Dish not found in your menu.");
            }
        } else {
            print("Usage: setPrice <dishName> <newPrice>");
        }
    }

    /**
     * Adds an item to an existing order.
     *
     * @param args the arguments for adding an item to an order, such as order ID and item ID
     */
    public static void addItem2Order(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to add an item to yout order.");
            return;
        }
        if (args.length == 2) {
            String itemType = args[0];
            String itemName = args[1];

            Customer customer = (Customer) system.getCurrentUser();
            Order currentOrder = customer.getCurrentOrder();
            if (currentOrder == null){
                print("You do not have an active order. Please create an order first.");
                return;
            }

            Restaurant restaurant = currentOrder.getRestaurant();

            try {
                if (itemType.equalsIgnoreCase("meal")) {
                    Meal meal = restaurant.getMealByName(itemName);
                    if (meal == null) {
                        print("Meal not found: " + itemName);
                        return;
                    }
                    currentOrder.addMeal(meal);
                } else if (itemType.equalsIgnoreCase("dish")) {
                    Dish dish = restaurant.getDishByName(itemName);
                    if (dish == null) {
                        print("Dish not found: " + itemName);
                        return;
                    }
                    currentOrder.addDish(dish);
                } else {
                    print("Invalid item type. Use 'meal' or 'dish'.");
                    return;
                }
                print("Item added to order successfully.");
            } catch (Exception e) {
                print("Failed to add item to order: " + e.getMessage());
            }

        } else {
            print("Usage: addItem2Order <itemType> <itemName>");
        }
    }

    /**
     * Ends the current order, finalizing it and processing it.
     *
     * @param args the arguments for ending an order, such as order ID
     */
    public static void endOrder(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to end an order.");
            return;
        }
        Customer customer = (Customer) system.getCurrentUser();
        if (customer.getCurrentOrder() == null) {
            print("You do not have an active order to end.");
            return;
        }
        Order order = customer.getCurrentOrder();
        Restaurant restaurant = order.getRestaurant();
        HashSet<Dish> dishes = new HashSet<>(order.getDishes()); // Assuming getDishes() returns an ArrayList<Dish>
        HashSet<Meal> meals = new HashSet<>(order.getMeals()); // Assuming Order has a method getMeals() that returns a HashSet<Meal>
        try {
            system.makeOrder(customer, restaurant, dishes, meals);
            print("Order ended successfully. Order ID: " + order.getId());
            customer.setCurrentOrder(null); // Clear the current order after ending it
        } catch (Exception e) {
            print("Failed to end order: " + e.getMessage());
        }

    }

    /**
     * Displays a list of available restaurants.
     */
    public static void showRestaurants(){
        if (system.getCurrentUser().getClass() != Customer.class && system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not permit you to show restaurants.");
            return;
        }

        Set<Restaurant> restaurants = system.getRestaurants();
        if (restaurants.isEmpty()) {
            print("No restaurants available.");
            return;
        }

        print("Available Restaurants:");
        for (Restaurant restaurant : restaurants) {
            System.out.println("    - " + restaurant.getName());
        }
    }

    public static void showOrders(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("Your user account does not permit you to show orders.");
            return;
        }

        Customer customer = (Customer) system.getCurrentUser();
        Set<Order> orders = customer.getHistory(system);

        if (orders.isEmpty()) {
            print("You have no past orders.");
            return;
        }
        
        if (args.length == 1) {
            String restaurantName = args[0];
            Restaurant restaurant = system.getRestaurantByName(restaurantName);
            if (restaurant == null) {
                print("Restaurant not found: " + restaurantName);
                return;
            }
            print("Your Past Orders at "+ restaurantName +" :");
            for (Order order : orders) {
                if (order.getRestaurant().equals(restaurant)) {
                    System.out.println( "    - " + order.toString());
                }
            }
        } else if (args.length == 0) {
            print("Your Past Orders:");
            for (Order order : orders) {
                System.out.println("    - Order ID: " + order.getId() + ", Restaurant: " + order.getRestaurant().getName());
            }
        } else {
            print("Usage: showOrders [<restaurantName>]");
            return;
        }
    }

    /**
     * Displays a list of popular restaurants based on customer ratings or order frequency.
     */
    public static void showPopularRestaurants() {
        if (system.getCurrentUser().getClass() != Customer.class && system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not permit you to show popular restaurants.");
            return;
        }

        Set<Restaurant> restaurants = system.getRestaurants();
        if (restaurants.isEmpty()) {
            print("No popular restaurants available.");
            return;
        }

        RestaurantSorter restaurantSorter = new RestaurantSorter();
        ArrayList<Restaurant> popularRestaurants = restaurantSorter.sort(new ArrayList<Restaurant>(restaurants));
        if (popularRestaurants.size() > 3) {
            popularRestaurants = new ArrayList<>(popularRestaurants.subList(0, 3));
        }

        print("Popular Restaurants:");
        for (Restaurant restaurant : popularRestaurants) {
            System.out.println("    - " + restaurant.getName());
        }
    }

    /**
     * Displays the total amount of money spent by the customer on orders.
     * If a restaurant name is provided, it shows the total spent at that specific restaurant.
     * @param args the arguments for showing money spent, such as restaurant name
     */
    public static void showMoneySpent(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("Your user account does not permit you to show money spent.");
            return;
        }

        Customer customer = (Customer) system.getCurrentUser();
        double totalSpent = 0.0;
        Set<Order> orders = customer.getHistory(system);

        if (args.length == 1) {
            String restaurantName = args[0];
            Restaurant restaurant = system.getRestaurantByName(restaurantName);
            if (restaurant == null) {
                print("Restaurant not found: " + restaurantName);
                return;
            }
            for (Order order : orders) {
                if (order.getRestaurant().equals(restaurant)) {
                    totalSpent += order.getPrice();
                }
            }
            print("Total money spent at " + restaurantName + ": " + totalSpent + "€");
        } else if (args.length == 0) {
            for (Order order : orders) {
                totalSpent += order.getPrice();
            }
            print("Total money spent: " + totalSpent+ "€");
        } else {
            print("Usage: showMoneySpent [<restaurantName>]");
        }
    }

    /**
     * Marks the courier as on duty, allowing them to accept orders.
     *
     * @param args the arguments for going on duty, such as courier ID
     */
    public static void onDuty() {
        if (system.getCurrentUser().getClass() != Courier.class) {
            print("You must be logged in as a Courier to go on duty.");
            return;
        }

        Courier courier = (Courier) system.getCurrentUser();
        if (courier.isOnDuty()) {
            print("You are already on duty.");
            return;
        }
        try {
            courier.setOnDuty(true);
            print("You are now on duty. You can accept new orders.");
        } catch (Exception e) {
            print("Failed to go on duty: " + e.getMessage());
        }
    }

    /**
     * Marks the courier as off duty, preventing them from accepting new orders.
     *
     * @param args the arguments for going off duty, such as courier ID
     */
    public static void offDuty(String... args) {
        if (system.getCurrentUser().getClass() != Courier.class) {
            print("You must be logged in as a Courier to go off duty.");
            return;
        }

        Courier courier = (Courier) system.getCurrentUser();
        if (!courier.isOnDuty()) {
            print("You are already off duty.");
            return;
        }
        try {
            courier.setOnDuty(false);
            print("You are now off duty. You will not receive new orders.");
        } catch (Exception e) {
            print("Failed to go off duty: " + e.getMessage());
        }
    }

    /**
     * Changes the address of the current user (Customer).
     *
     * @param args the arguments for changing the address, such as new coordinates
     */
    public static void changeAddress(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to change your address.");
            return;
        }

        if (args.length == 2) {
            try {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                Customer customer = (Customer) system.getCurrentUser();
                customer.setAdress(new Location(x, y));
                print("Address changed successfully to: (" + x + ", " + y + ")");
            } catch (NumberFormatException e) {
                print("Error: Coordinates must be valid numbers.");
            }
        } else {
            print("Usage: changeAddress <x> <y>");
        }
    }

    /**
     * Changes the phone number of the current user (Customer).
     *
     * @param args the arguments for changing the phone number, such as new phone number
     */
    public static void changePhoneNumber(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to change your phone number.");
            return;
        }

        if (args.length == 1) {
            String newPhoneNumber = args[0];
            Customer customer = (Customer) system.getCurrentUser();
            try {
            customer.setPhoneNumber(newPhoneNumber);
            print("Phone number changed successfully to: " + newPhoneNumber);
            } catch (Exception e) {
                print("Failed to change phone number: " + e.getMessage());
            }
        } else {
            print("Usage: changePhoneNumber <newPhoneNumber>");
        }
    }

    /**
     * Sets the consent for receiving notifications for the current user (Customer).
     *
     * @param args the arguments for setting consent, such as "yes" or "no"
     */
    public static void consentNotifications(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to set consent for notifications.");
            return;
        }

        if (args.length == 1) {
            String consent = args[0].toLowerCase();
            Customer customer = (Customer) system.getCurrentUser();
            if (consent.equals("yes") || consent.equals("true") || consent.equals("1") || consent.equals("y")) {
                customer.setNotificationsConsent(true);
                print("Consent for notifications set to yes.");
            } else if (consent.equals("no") || consent.equals("false") || consent.equals("0") || consent.equals("n")) {
                customer.setNotificationsConsent(false);
                print("Consent for notifications set to no.");
            } else {
                print("Invalid argument. Use 'yes' or 'no'.");
            }
        } else {
            print("Usage: consentNotifications <yes/no>");
        }
    }

    /**
     * Finds a deliverer for the current order based on the provided criteria.
     *
     * @param args the arguments for finding a deliverer, such as order ID or delivery location
     */
    public static void setDeliveryPolicy(String... args) {
        // Do smth
    }

    /**
     * Sets the profit policy for the system, defining how profits are calculated and distributed.
     *
     * @param args the arguments for setting the profit policy, such as policy details
     */
    public static void setProfitPolicy(String... args) {
        // Do smth
    }

    public static FidelityCard string2FidelityCard(String cardType, Customer owner) {
        switch (cardType){
            case "basic":
                return new BasicCard(owner);
            case "lottery":
                return new LotteryCard(owner);
            case "point":
                return new PointCard(owner);
            default:
                return null; // Invalid card type
        }
    }

    /**
     * Associates a fidelity card with the user's account.
     *
     * @param args the arguments for associating a card, such as card number and user ID
     */
    public static void associateCard(String... args) {
        if (system.getCurrentUser().getClass() != Customer.class && system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not permit you to associate a fidelity card.");
            return;
        }
        if (args.length == 1 && system.getCurrentUser().getClass() == Customer.class) {
            String cardType = args[0].toLowerCase();
            Customer customer = (Customer) system.getCurrentUser();
            try {
                FidelityCard card = string2FidelityCard(cardType,customer);
                if (card == null) {
                    print("Fidelity card type not found: " + cardType);
                    return;
                }
                customer.setFidelityCard(card);
                print("Fidelity card associated successfully with your account.");
            } catch (Exception e) {
                print("Failed to associate fidelity card: " + e.getMessage());
            }
        } else if (args.length == 2 && system.getCurrentUser().getClass() == Manager.class) {
            String cardType = args[0].toLowerCase();
            String customerUsername = args[1];
            Customer customer = (Customer) system.getUserMap().get(customerUsername);
            if (customer == null) {
                print("Customer not found: " + customerUsername);
                return;
            }
            FidelityCard card = string2FidelityCard(cardType, customer);
            if (card == null) {
                print("Fidelity card type not found: " + cardType);
                return;
            }
            customer.setFidelityCard(card);
            print("Fidelity card associated successfully with customer: " + customerUsername);
        } else {
            print("Usage: associateCard <cardNumber> [<customerUsername>]");
        }
    }

    /**
     * Displays the deliveries made by couriers.
     */
    public static void showCourierDeliveries() {
        // Do smth
    }

    /**
     * Displays the top restaurants based on their performance.
     */
    public static void showRestaurantTop() {
        // Do smth
    }

    /**
     * Displays the list of customers registered in the system.
     */
    public static void showCustomers() {
        // Do smth
    }

    /**
     * Displays a specific menu item based on the provided arguments.
     *
     * @param args the arguments for showing a menu item, such as item ID or name
     */
    public static void showMenuItems(String... args) {
        if (system.getCurrentUser().getClass() != Manager.class && system.getCurrentUser().getClass() != Restaurant.class && system.getCurrentUser().getClass() != Customer.class) {
            print("Your user account does not permit you to show a menu item.");
            return;
        }

        if (args.length == 0 && system.getCurrentUser().getClass() == Restaurant.class ) {
            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            System.out.println(restaurant.getMenu().toString());
        } else if (args.length == 1) {
            String restaurantName = args[0];
            Restaurant restaurant = system.getRestaurantByName(restaurantName);
            if (restaurant == null) {
                print("Restaurant not found: " + restaurantName);
                return;
            }
            System.out.println(restaurant.getMenu().toString());
        } else {
            print("Usage: showMenuItem <restaurantName>");
        }
    }

    /**
     * Displays the total profit made by the system.
     *
     * @param args the arguments for showing total profit, such as date range or filters
     */
    public static void showTotalProfit(String... args) {
        // Do smth
    }

    /**
     * Runs a test scenario based on the provided arguments.
     *
     * @param args the arguments for running the test, such as test scenario file
     */
    public static void runTest(String... args) {
        // Do smth
    }
    
}