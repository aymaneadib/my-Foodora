package cli;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
            case "ADDDISHRESTAURANTMENU":
                addDishRestauarantMenu(args);
                break;
            case "CREATEMEAL":
                createMeal(args);
                break;
            case "ADDDISH2MEAL":
                addDish2Meal(args);
                break;
            case "SHOWMEAL":
                showMeal(args);
                break;
            case "SAVEMEAL":
                saveMeal(args);
                break;
            case "SETSPECIALOFFER":
                setSpecialOffer(args);
                break;
            case "REMOVEFROMSPECIALOFFER":
                removeFromSpecialOffer(args);
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
                onDuty(args);
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
            case "SHOWCOURIERDELIVERIES":
                showCourierDeliveries();
                break;
            case "SHOWRESTAURANTTOP":
                showRestaurantTop();
                break;
            case "SHOWCUSTOMERS":
                showCustomers();
                break;
            case "SHOWMENUITEM":
                showMenuItem(args);
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
        System.out.println("    - SHOWCOURIERDELIVERIES - Show your deliveries made.");
    }

    /**
     * Prints the help message for the Restaurant user type.
     * This method can be extended to include specific commands and instructions for restaurants.
     */
    public static void printRestaurantHelp(){
        System.out.println("Restaurant Commands Available :");
        System.out.println("    - ADDDISHRESTAURANTMENU <dishType> <dishName> <unitPrice> <isVege [y/n]> <glutenFree [y/n]> - Add a dish to the restaurant's menu.");
        System.out.println("    - CREATEMEAL <mealType> <mealName> <dish1Name> <dish2Name> [<dish3Name>] - Create a meal with specified dishes and add it to your menu.");
    }

    /**
     * Prints the help message for the Customer user type.
     * This method can be extended to include specific commands and instructions for customers.
     */
    public static void printCustomerHelp(){
        System.out.println("Customer Commands Available :");
    }

    /**
     * Prints the help message for the Manager user type.
     * This method can be extended to include specific commands and instructions for managers.
     */
    public static void printManagerHelp(){
        // Do smth
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
    public static void addDishRestauarantMenu(String... args) {
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            System.out.println("You must be logged in as a Restaurant to add a dish to a menu.");
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
            System.out.println("You must be logged in as a Restaurant to create a meal.");
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
                        Dish dish1 = system.getDishByName(dish1Name, restaurant );
                        Dish dish2 = system.getDishByName(dish2Name, restaurant);
                        Dish dish3 = system.getDishByName(dish3Name , restaurant);

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
                        Dish dish1 = system.getDishByName(dish1Name, restaurant);
                        Dish dish2 = system.getDishByName(dish2Name, restaurant);

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
     * Adds a dish to an existing meal.
     *
     * @param args the arguments for adding a dish to a meal, such as meal ID and dish ID
     */
    public static void addDish2Meal(String... args) {
        // Do smth
    }

    /**
     * Displays the details of a specific meal.
     *
     * @param args the arguments for showing a meal, such as meal ID
     */
    public static void showMeal(String... args) {
        // Do smth
    }

    /**
     * Saves the current meal to the system.
     *
     * @param args the arguments for saving a meal, such as meal ID or name
     */
    public static void saveMeal(String... args) {
        // Do smth
    }

    /**
     * Sets a special offer for a meal or dish.
     *
     * @param args the arguments for setting a special offer, such as meal ID and offer details
     */
    public static void setSpecialOffer(String... args) {
        // Do smth
    }

    /**
     * Removes a meal or dish from the special offer.
     *
     * @param args the arguments for removing from special offer, such as meal ID or dish ID
     */
    public static void removeFromSpecialOffer(String... args) {
        // Do smth
    }

    /**
     * Creates a new order with the provided details.
     *
     * @param args the arguments for creating an order, such as customer ID and meal ID
     */
    public static void createOrder(String... args) {
        // Do smth
    }

    /**
     * Adds an item to an existing order.
     *
     * @param args the arguments for adding an item to an order, such as order ID and item ID
     */
    public static void addItem2Order(String... args) {
        // Do smth
    }

    /**
     * Ends the current order, finalizing it and processing it.
     *
     * @param args the arguments for ending an order, such as order ID
     */
    public static void endOrder(String... args) {
        // Do smth
    }

    /**
     * Marks the courier as on duty, allowing them to accept orders.
     *
     * @param args the arguments for going on duty, such as courier ID
     */
    public static void onDuty(String... args) {
        // Do smth
    }

    /**
     * Marks the courier as off duty, preventing them from accepting new orders.
     *
     * @param args the arguments for going off duty, such as courier ID
     */
    public static void offDuty(String... args) {
        // Do smth
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

    /**
     * Associates a fidelity card with the user's account.
     *
     * @param args the arguments for associating a card, such as card number and user ID
     */
    public static void associateCard(String... args) {
        // Do smth
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
    public static void showMenuItem(String... args) {
        // Do smth
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