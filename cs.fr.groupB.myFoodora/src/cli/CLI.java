package cli;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import user.*;
import food.*;
import fidelity.*;
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
    private static boolean registering = false;
    private static boolean resolvingPendingOrders = false;
    private static Order pendingOrder = null;
    private static String userTypeRegistering;
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
            System.out.print("\n> ");
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
    	UserFactory userFactory = new UserFactory();
    	// Reading initialization file
    	String path = "./eval/my_foodora.ini";
    	FileReader file = null;
    	BufferedReader reader = null;
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	try {
    		file = new FileReader(path);     // a FileReader for reading byte−by−byte
    		reader = new BufferedReader(file); // wrapping a FileReader into a BufferedReader for reading line−by−line
    		
    		String line = "";
    		while ((line = reader.readLine()) != null) { // read the file line−by−line
    			lines.add(line);
    		}

    	} catch (IOException e) {
    		print("Error reading the initialization file " + path + " : "+ e.getMessage());
    		return;
    	}
    	
    	if (reader != null) {
			try {reader.close();}
			catch (IOException e) {
			}
		}

    	// Dealing with commands
    	try {
    	
    		for (int i = 0; i < lines.size(); i++) {
        		if (lines.get(i).equals("") || lines.get(i).equals("\n")) continue;
        		if (lines.get(i).substring(0, 1).equals(";")) continue;
        		
        		String section = lines.get(i).substring(1, lines.get(i).length() - 1);
        		User user = null;
        		
        		switch(section.toUpperCase()) {
        		
        		case "MANAGER":
					user = (Manager) userFactory.createUser("manager", lines.get(i+1).split("=")[1], lines.get(i+2).split("=")[1],
							lines.get(i+3).split("=")[1], lines.get(i+4).split("=")[1]);
					i += 4;
        			break;
        			
        		case "CUSTOMER":
					user = (Customer) userFactory.createUser("customer", lines.get(i+1).split("=")[1], lines.get(i+2).split("=")[1],
							lines.get(i+3).split("=")[1], lines.get(i+4).split("=")[1], lines.get(i+5).split("=")[1],
							lines.get(i+6).split("=")[1], lines.get(i+7).split("=")[1], lines.get(i+8).split("=")[1],
							lines.get(i+9).split("=")[1]);
					i += 9;
        			break;
        			
        		case "COURIER":
        			user = (Courier) userFactory.createUser("courier", lines.get(i+1).split("=")[1], lines.get(i+2).split("=")[1],
        					lines.get(i+3).split("=")[1], lines.get(i+4).split("=")[1], lines.get(i+5).split("=")[1],
        					lines.get(i+6).split("=")[1], lines.get(i+7).split("=")[1]);
        			((Courier) user).setOnDuty(Boolean.parseBoolean(lines.get(i+8).split("=")[1]));
					i += 8;
        			break;
        			
        		case "RESTAURANT":
        			user = (Restaurant) userFactory.createUser("restaurant", lines.get(i+1).split("=")[1], lines.get(i+2).split("=")[1],
        					lines.get(i+3).split("=")[1], lines.get(i+4).split("=")[1], lines.get(i+5).split("=")[1]);
					i += 5;
        			break;
        			
        		default:
        			print("Error reading the initialization file " + path + " : "+ "unrecognized section in file.");
        			break;
        		}
        		
        		system.addUser(user);
        	}
    		
    	} catch (BadUserCreationException e) {
			print("Error: " + e.getMessage());
		}
    	
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
        System.out.println("  RUNTEST <testScenarioFile> - execute the list of CLUI commands contained in the testScenario file passed as argument.");
        System.out.println("  LOGIN <username> <password>- Login to an existing user account");
        System.out.println("  REGISTER <userType> - Register your new account with the necessary information");
        System.out.println("  SETUP <restaurantQuantity> <customerQuantity> <courierQuantity> - Generates random users based on quantity arguments.");
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
    	if (CLI.registering) {
    		String[] args = input.split("\\s+");
        	completeRegistration(args);
        	return;
        }
    	
    	if (CLI.resolvingPendingOrders) {
    		String[] args = input.split("\\s+");
        	resolvePendingOrders(args);
        	return;
        }
    	
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
            case "SETGENERICDISCOUNTFACTOR":
                setGenericDiscountFactor(args);
                break;
            case "SETSPECIALDISCONTFACTOR":
                setSpecialDiscountFactor(args);
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
            case "DISPLAYCURRENTORDER":
            	displayCurrentOrder();
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
            case "DISPLAYFIDELITYCARD":
                displayFidelityCard();
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
            case "SETUP":
            	setup(args);
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
	    System.out.println("    - RUNTEST <testScenarioFile> - execute the list of CLUI commands contained in the testScenario file passed as argument.");
	    System.out.println("    - LOGIN <username> <password> - Log in with the specified username and password.");
        System.out.println("    - REGISTER <userType> - Register a new user account. User types can be: CUSTOMER, RESTAURANT, COURIER.");
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
        System.out.println("    - ACCEPTORDER - Accepts order and the refuses the others.");
        System.out.println("    - REFUSEORDER - Refuses chosen order.");
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
        System.out.println("    - SETGENERICDISCOUNTFACTOR <discountFactor> - Set the generic discount factor for your restaurant.");
        System.out.println("    - SETSPECIALDISCONTFACTOR <discountFactor> - Set the special discount factor for your restaurant.");
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
        System.out.println("    - DISPLAYFIDELITYCARD - Display your fidelity card information.");
    }

    /**
     * Prints the help message for the Manager user type.
     * This method can be extended to include specific commands and instructions for managers.
     */
    public static void printManagerHelp(){
        System.out.println("Manager Commands Available :");
        System.out.println("    - SHOWMENUITEMS <restaurantName> - Show details of a specific restaurant's menu.");
        System.out.println("    - ASSOCIATECARD <cardNumber> <customerUsername> - Associate a fidelity card with a customer account.");
        System.out.println("    - REGISTER <userType> - Register a new user account. User types can be: MANAGER, CUSTOMER, RESTAURANT, COURIER.");
        System.out.println("    - SHOWCOURIERDELIVERIES - Display the list of couriers sorted in decreasing order w.r.t. the number of completed deliveries.");
        System.out.println("    - SHOWRESTAURANTTOP - Display list of restaurants sorted in decreasing order w.r.t. the number of delivered orders.");
        System.out.println("    - SHOWCUSTOMERS - Display the list of customers.");
        System.out.println("    - SHOWTOTALPROFIT <startDate YYYY-MM-DD> <endDate YYYY-MM-DD> - Show the total profit of the system. Time interval is optional.");
        System.out.println("    - SETDELIVERPOLICY <delPolicy> - set the delivery policy of the system : FairOccupationDelivery, FastestDelivery.");
        System.out.println("    - SETPROFITPOLICY <profitPolicy> - set the profit policy of the system : DeliveryCostOriented, MarkupPercentageOriented, ServiceFeeOriented.");
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
     * Creates random users based on quantities specified by the user.
     *
     * @param args the quantities of each user to be created : restaurantQuantity, customerQuantity, courierQuantity
     */
    public static void setup(String... args){
    	if (system.getCurrentUser() != null) {
    		print("Error: you must be logged out to setup new users.");
    		return;
    	}
    	if (args.length != 3) {
    		print("Usage: SETUP <restaurantQuantity> <customerQuantity> <courierQuantity> - Generates random users based on quantity arguments.");
    		return;
    	}
    	
    	// Getting parameters
    	int restaurantQuantity;
    	int customerQuantity;
    	int courierQuantity;
    	
    	try {
    		restaurantQuantity = Integer.parseInt(args[0]);
        	customerQuantity = Integer.parseInt(args[1]);
        	courierQuantity = Integer.parseInt(args[2]);
    	} catch(NumberFormatException e) {
    		print("Error: invalid number type.");
    		print("Usage: SETUP <restaurantQuantity> <customerQuantity> <courierQuantity> - Generates random users based on quantity arguments.");
    		return;
    	}
    	
    	
    	if ((restaurantQuantity <= 0) || (restaurantQuantity > 100) ||
    	    (customerQuantity <= 0) || (customerQuantity > 100) ||
    	    (courierQuantity <= 0) || (courierQuantity > 100)) {
    		print("Error: Select quantities between 1 and 100.");
    		return;
    	}
    	
    	// Creating users
    	BasicScenarioGenerator generator = new BasicScenarioGenerator();
    	try {
			generator.createRandomUsers(restaurantQuantity, customerQuantity, courierQuantity);
		} catch (BadUserCreationException | BadNumberOfArgumentsException | BadDishTypeCreationException
				| BadArgumentTypeException | BadMealTypeCreationException | UnrecognizedDishException
				| BadMealFormulaException e) {
			print("Error creating users: "  + e.getMessage());
		}
    	
    	// Sending created users to the system
    	User.clearUsernamesFromUsernamesUsed();
    	Person.clearPhonesFromPhonesUsed();
    	Customer.clearEmailsFromEmailsUsed();
    	system.setRestaurants(generator.getCreatedRestaurants());
    	system.setCustomers(generator.getCreatedCustomers());
    	system.setCouriers(generator.getCreatedCouriers());
    	system.setUserMap(generator.getCreatedUserMap());
    	
    	print("Created " + restaurantQuantity + " restaurants, " + customerQuantity + " customers and " + courierQuantity + " couriers.");
    }

    /**
     * Handles user login by processing the provided username and password.
     * This method can be extended to include authentication logic.
     *
     * @param args the username and password for login
     */
    public static void login(String... args) {
    	if (args.length == 2) {
    		
    		if (system.getCurrentUser() == null) {
    			// Try to login
    			try {
    				system.login(args[0], args[1]);
    				print("User " + args[0] + " logged in.");
    				
    				// If it's a customer, tries to print new notifications and clear them
    				if (system.getCurrentUser() instanceof Customer) {
    					if (((Customer) system.getCurrentUser()).isNotificationsConsent() == true &&
    							!((Customer) system.getCurrentUser()).getNotifications().equals("")) {
    						print(((Customer) system.getCurrentUser()).getName() + ", check your new notifications:");
    						print(((Customer) system.getCurrentUser()).getNotifications());
    						((Customer) system.getCurrentUser()).clearNotifications();
    					}
    				}
    				
    				if (system.getCurrentUser() instanceof Courier) {
    					showPendingOrdersToResolve();
    				}
    			// Print error message's if doesn't succeed
    			} catch (UserNotFoundException e) {
    				print("Error: " + e.getMessage());
    			} catch (IncorrectCredentialsException e) {
    				print("Error: " + e.getMessage());
    			}
    		} else {
    			print("Error: There is already a user logged into the system.");
    		}		
    		
    	} else {
    		print("Usage: LOGIN <username> <password>");
    	}
    }
    
    public static void completeRegistration(String...args) {
    	if (!CLI.registering) {
    		print("Error: There's no registration in course.");
    	}
    	CLI.registering = false;
		
		// Tries to create the user
		try {
			if (args.length == 9) {
				if (args[8] == "yes") args[8] = "true";
				
				if (args[8] == "no") args[8] = "false";
			}
			User newUser = system.getUserFactory().createUser(CLI.userTypeRegistering, args);
			system.addUser(newUser);
			print("User " + newUser.getUsername() + " registered successfully!");
			print("To use the new user, you need to login.");
		} catch (BadUserCreationException e) {
			print("Error: " + e.getMessage());
			if (e.getMessage().contains("Email already used") || e.getMessage().contains("Phone number already used")) {
				User.removeUsernameFromUsernamesUsed(args[2]);
			}
		}
		
		CLI.userTypeRegistering = "";
    }

    /**
     * Registers a new user account based on the provided arguments.
     *
     * @param args the arguments for registration, such as user type and details
     */
    public static void register(String... args) {
    	// If there's one parameter
        if (args.length == 1) {
        	CLI.registering = false;
        	
        	// Verifies the user type to print the correct instructions
        	switch (args[0].toUpperCase()) {
        	
        	case "CUSTOMER":
        		print("Enter the Customer information.");
        		print("Usage: <name> <surname> <username> <password> <phoneNumber> <email> <addresX> <addresY> <consentNotifications yes/no>");
        		break;
        		
        	case "RESTAURANT":
        		print("Enter the Restaurant information.");
        		print("Usage: <name> <username> <password> <addresX> <addresY>");
        		break;
        		
        	case "COURIER":
        		print("Enter the Courier information.");
        		print("Usage: <name> <surname> <username> <password> <phoneNumber> <addresX> <addresY>");
        		break;
        		
        	case "MANAGER":
        		// A manager can only be created by another manager
        		if (system.getCurrentUser() instanceof Manager) {
        			print("Enter the Manager information.");
        			print("Usage: <name> <surname> <username> <password>");	
        		} else {
        			print("Error: You are not authorized to create a manager.");
        			return;
        		}
        		break;
        		
        	default:
        		if (system.getCurrentUser() instanceof Manager) {
        			print("Error: User type " + args[0] + " not recognized. User types can be: MANAGER, CUSTOMER, RESTAURANT, COURIER.");
        		} else {
        			print("Error: User type " + args[0] + " not recognized. User types can be: CUSTOMER, RESTAURANT, COURIER.");
        		}
        		return;
        	
        	}
        	
        	CLI.registering = true;
        	CLI.userTypeRegistering = args[0];
			
        } else {
        	if (system.getCurrentUser() instanceof Manager) {
        		print("Usage: REGISTER <userType> - User types can be: MANAGER, CUSTOMER, RESTAURANT, COURIER.");
        	} else {
        		print("Usage: REGISTER <userType> - User types can be: CUSTOMER, RESTAURANT, COURIER.");
        	}
        }
    }

    /**
     * Logs out the current user from the system.
     */
    public static void logout() {
    	if (system.getCurrentUser() != null) {
    		print("User " + system.getCurrentUser().getUsername() + " logged out.");
    		system.logout();
    	} else {
    		print("There's no user logged into the system.");
    	}
    }

    /**
     * Adds a dish to the restaurant's menu.
     *
     * @param args the arguments for adding a dish, such as dish name, price, and description
     */
    public static void createDish(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to add a dish to a menu.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to create a meal.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to remove a meal from a menu.");
    		return;
    	}
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
     * Sets the generic discount factor for the restaurant.
     * @param args
     */
    public static void setGenericDiscountFactor(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to set the generic discount factor.");
    		return;
    	}
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to set the generic discount factor.");
            return;
        }
        Restaurant restaurant = (Restaurant) system.getCurrentUser();
        if (args.length == 1) {
            try {
                double discountFactor = Double.parseDouble(args[0]);
                restaurant.setGeneralDiscount(discountFactor);
                print("Generic discount factor set to: " + discountFactor);
            } catch (Exception e) {
                print("Error: Invalid discount factor. Please provide a valid number.");
            }
        } else {
            print("Usage: setGenericDiscountFactor <discountFactor>");
        }
    }

    /**
     * Sets the special discount factor for the restaurant.
     * @param args
     */
    public static void setSpecialDiscountFactor(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to set the special discount factor.");
    		return;
    	}
        if (system.getCurrentUser().getClass() != Restaurant.class) {
            print("You must be logged in as a Restaurant to set the special discount factor.");
            return;
        }
        Restaurant restaurant = (Restaurant) system.getCurrentUser();
        if (args.length == 1) {
            try {
                double discountFactor = Double.parseDouble(args[0]);
                restaurant.setSpecialDiscount(discountFactor);
                print("Special discount factor set to: " + discountFactor);
            } catch (Exception e) {
                print("Error: Invalid discount factor."+e.getMessage());
            }
        } else {
            print("Usage: setSpecialDiscountFactor <discountFactor>");
        }
    }

    /**
     * Removes a dish from the restaurant's menu.
     *
     * @param args the arguments for removing a dish, such as dish name
     */
    public static void removeDish(String... args){
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to remove a dish from a menu.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to show a meal.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to show a dish.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to set a special offer.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to remove from special offer.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to create an order.");
    		return;
    	}
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to create an order.");
            return;
        }
        if (((Customer)system.getCurrentUser()).getCurrentOrder() != null) {
            print("You already have an active order. Please complete it before creating a new one.");
            return;
        }
        if (CLI.pendingOrder != null) {
        	print("There is alreay one order in course in the system that must be accept/refused by a Courier.");
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Restaurant to set the price of a dish.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to add an item to yout order.");
    		return;
    	}
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
            
            if (customer.getCurrentOrder().getCurrentStatus().equals("COMPLETED AND WAITING FOR ACCEPTANCE OF A COURIER")) {
            	print("You order is already completed, the system is searching for a courier.");
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
     * See the items added into the current order.
     *
     */
    public static void displayCurrentOrder() {
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to see the current order.");
    		return;
    	}
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to see the current order.");
            return;
        }

        Customer customer = (Customer) system.getCurrentUser();
        Order currentOrder = customer.getCurrentOrder();
        if (currentOrder == null){
            print("You do not have an active order. Please create an order first.");
            return;
        }

        // Printing dishes
        boolean emptyDishesCurrentOrder = true;
        print("---------- Dishes in your order ----------");
        for (Dish dish : currentOrder.getDishes()) {
        	print(dish.toString());
        	emptyDishesCurrentOrder = false;
        }
        if (emptyDishesCurrentOrder) {
        	print("No dishes found in your order.");
        }
        
        // Printing meals
        boolean emptyMealsCurrentOrder = true;
        print("---------- Meals in your order -----------");
        
        for (Meal meal : currentOrder.getMeals()) {
        	print(meal.toString());
        	emptyMealsCurrentOrder = false;
        }
        if (emptyMealsCurrentOrder) {
        	print("No meals found in your order.");
        }
        
        // Printing order status
        print("------------------------------------------");
        
        print("Order status: " + currentOrder.getCurrentStatus());
    }

    /**
     * Ends the current order, finalizing it and processing it.
     *
     * @param args the arguments for ending an order, such as order ID
     */
    public static void endOrder(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to end an order.");
    		return;
    	}
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
        	system.makeOrder(order, dishes, meals);
            print("Order ended successfully. Order ID: " + order.getId());
            print("We will find a courier for you order.");
            customer.getCurrentOrder().setCurrentStatus("COMPLETED AND WAITING FOR ACCEPTANCE OF A COURIER");
            CLI.pendingOrder = customer.getCurrentOrder();
        } catch (AvailableCourierNotFoundException e) {
            print("Failed to end order: " + e.getMessage());
        }

    }
    
    /**
     * Displays all the list of orders to accept/refuse
     *
     */
    private static void showPendingOrdersToResolve() {
        if (system.getCurrentUser() == null) {
            return;
        }
    	if (((Courier) system.getCurrentUser()).getPendingOrders().size() > 0) {
    		CLI.resolvingPendingOrders = true;
    		print("-------- You must accept or refuse the following orders --------");
    		for (Order order : ((Courier) system.getCurrentUser()).getPendingOrders()) {
    			print(order.toString());
    		}
    	} else {
    		CLI.resolvingPendingOrders = false;
    	}
    }
    
    public static void resolvePendingOrders(String... args) {
    	if (args.length != 2) {
    		print("Usage: ACCEPTORDER - Accepts order and the refuses the others.");
    		print("Usage: REFUSEORDER - Refuses chosen order.");
    	}

        if (system.getCurrentUser() == null) {;
            return;
        }
    	
    	switch (args[0].toUpperCase()) {
    		
    	case "REFUSEORDER":
    		try {
    			boolean orderFound = ((Courier) system.getCurrentUser()).refuseOrder(Integer.parseInt(args[1]));
    			if (orderFound) {
    				print("Order ID " + args[1] + " refused.");
    			}
    		} catch(NumberFormatException e) {
    			print("Error: You must use a number as the ID of the Order.");
    		}
    		break;
    		
    	case "ACCEPTORDER":
    		try {
    			boolean orderFound = ((Courier) system.getCurrentUser()).acceptOrder(Integer.parseInt(args[1]));
    			if (orderFound) {
    				print("Order ID " + args[1] + " accepted.");
    				CLI.pendingOrder.getCustomer().setCurrentOrder(null);
    				CLI.pendingOrder = null;
    			}
    		} catch(NumberFormatException e) {
    			print("Error: You must use a number as the ID of the Order.");
    		}
    		break;
    		
    	case "LOGOUT":
    		logout();
    		break;
    		
    	default:
    		print("Usage: ACCEPTORDER - Accepts order and the refuses the others.");
    		print("Usage: REFUSEORDER - Refuses chosen order.");
    		
    	}
    	
    	if (CLI.pendingOrder != null) {
    		if (CLI.pendingOrder.getPossibleCouriers().size() == 0) {
    			CLI.pendingOrder.getCustomer().setCurrentOrder(null);
    			CLI.pendingOrder.setCurrentStatus("INCOMPLETE, NO COURIER FOUND");
    			CLI.pendingOrder = null;
    		}
    	}
    	
    	showPendingOrdersToResolve();
    }

    /**
     * Displays the fidelity card information for the current customer.
     *
     * @param args the arguments for displaying the fidelity card, if any
     */
    public static void displayFidelityCard() {
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to display your fidelity card.");
    		return;
    	}
        if (system.getCurrentUser().getClass() != Customer.class) {
            print("You must be logged in as a Customer to display your fidelity card.");
            return;
        }
        Customer customer = (Customer) system.getCurrentUser();
        FidelityCard card = customer.getFidelityCard();
        print("Fidelity Card Details: " + card.toString());
    }

    /**
     * Displays a list of available restaurants.
     */
    public static void showRestaurants(){
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to show restaurants.");
    		return;
    	}
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
            print("    - " + restaurant.getName());
        }
    }

    public static void showOrders(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("Your must be logged in as an customer to see your orders.");
    		return;
    	}
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
                    print( "    - " + order.toString());
                }
            }
        } else if (args.length == 0) {
            print("Your Past Orders:");
            for (Order order : orders) {
                print("    - Order ID: " + order.getId() + ", Restaurant: " + order.getRestaurant().getName());
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
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to show popular restaurants: you are not logged in.");
    		return;
    	}
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
            print("    - " + restaurant.getName());
        }
    }

    /**
     * Displays the total amount of money spent by the customer on orders.
     * If a restaurant name is provided, it shows the total spent at that specific restaurant.
     * @param args the arguments for showing money spent, such as restaurant name
     */
    public static void showMoneySpent(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to show money spent: you are not logged in.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Courier to go on duty.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Courier to go off duty.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to change your address.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to change your phone number.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("You must be logged in as a Customer to set consent for notifications.");
    		return;
    	}
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
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to set a delivery policy: you are not logged in.");
    		return;
    	}
    	if (system.getCurrentUser().getClass() != Customer.class && system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not permit you to set a delivery policy.");
            return;
        }
    	
    	switch (args[0].toUpperCase()) {
    	
    	case "FAIROCCUPATIONDELIVERY":
    		system.setDeliveryStrategy(new FairOccupationDelivery());
    		break;
    		
    	case "FASTESTDELIVERY":
    		system.setDeliveryStrategy(new FastestDelivery());
    		break;
    		
    	default:
    		print("Error: Unrecognized delivery policy.");
    		return;
    	}
    	
    	print("Delivery policy " + args[0] + " setted.");
    }

    /**
     * Sets the profit policy for the system, defining how profits are calculated and distributed.
     *
     * @param args the arguments for setting the profit policy, such as policy details
     */
    public static void setProfitPolicy(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to set a profit policy: you are not logged in.");
    		return;
    	}
    	if (system.getCurrentUser().getClass() != Customer.class && system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not permit you to set a profit policy.");
            return;
        }
    	
    	switch (args[0].toUpperCase()) {
    	
    	case "DELIVERYCOSTORIENTED":
    		system.setProfitStrategy(new TargetProfitDeliveryCostOriented());
    		break;
    		
    	case "MARKUPPERCENTAGEORIENTED":
    		system.setProfitStrategy(new TargetProfitMarkupPercentageOriented());
    		break;
    		
    	case "SERVICEFEEORIENTED":
    		system.setProfitStrategy(new TargetProfitServiceFeeOriented());
    		break;
    		
    	default:
    		print("Error: Unrecognized profit policy.");
    		return;
    	}
    	
    	print("Profit policy " + args[0] + " setted.");
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
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to associate a fidelity card: you are not logged in.");
    		return;
    	}
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
     * Displays the the list of couriers sorted in decreasing order w.r.t. the number of completed deliveries.
     */
    public static void showCourierDeliveries() {
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not allow you to see the top couriers: you are not logged in.");
    		return;
    	}
    	if (system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not allow you to see the top couriers.");
            return;
        }
    	
    	ArrayList<Courier> couriers = ((Manager) system.getCurrentUser()).sortCouriers(system);
    	if (couriers.size() > 0) {
    		for(Courier courier : couriers) {
        		print(courier.toString());
        	}
    	} else {
    		print("Error: no courier found.");
    	}
    }

    /**
     * Displays the top restaurants based on their performance.
     */
    public static void showRestaurantTop() {
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not allow you to see the top restaurants: you are not logged in.");
    		return;
    	}
    	if (system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not allow you to see the top restaurants.");
            return;
        }
    	
    	ArrayList<Restaurant> restaurants = ((Manager) system.getCurrentUser()).sortRestaurants(system);
    	if (restaurants.size() > 0) {
    		for(Restaurant restaurant : restaurants) {
        		print(restaurant.getName() + " with " + restaurant.getOrderCounter() + " orders.");
        	}
    	} else {
    		print("Error: no restaurant found.");
    	}
    }

    /**
     * Displays the list of customers registered in the system.
     */
    public static void showCustomers() {
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not allow you to see the list of customers: you are not logged in.");
    		return;
    	}
    	if (system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not allow you to see the list of customers.");
            return;
        }
    	
    	Set<Customer> customers = system.getCustomers();
    	if (customers.size() > 0) {
    		for(Customer customer : customers) {
        		print(customer.toString());
        	}
    	} else {
    		print("Error: no restaurant found.");
    	}
    }

    /**
     * Displays a specific menu item based on the provided arguments.
     *
     * @param args the arguments for showing a menu item, such as item ID or name
     */
    public static void showMenuItems(String... args) {
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not permit you to show a menu item: you are not logged in.");
    		return;
    	}
        if (system.getCurrentUser().getClass() != Manager.class && system.getCurrentUser().getClass() != Restaurant.class && system.getCurrentUser().getClass() != Customer.class) {
            print("Your user account does not permit you to show a menu item.");
            return;
        }

        if (args.length == 0 && system.getCurrentUser().getClass() == Restaurant.class ) {
            Restaurant restaurant = (Restaurant) system.getCurrentUser();
            print(restaurant.getMenu().toString());
        } else if (args.length == 1) {
            String restaurantName = args[0];
            Restaurant restaurant = system.getRestaurantByName(restaurantName);
            if (restaurant == null) {
                print("Restaurant not found: " + restaurantName);
                return;
            }
            print(restaurant.getMenu().toString());
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
    	if (system.getCurrentUser() == null) {
    		print("Your user account does not allow you to see the list of customers: you are not logged in.");
    		return;
    	}
    	if (system.getCurrentUser().getClass() != Manager.class) {
            print("Your user account does not allow you to see the list of customers.");
            return;
        }
    	
    	
    	double profit = 0;
    	
    	if (args.length == 2) {
    		String date1 = args[0];
    		String date2 = args[1];
    		if (args[0].contains("/")){
    			// Then it is in the DD/MM/YYYY format
    			date1 = args[0].substring(6, 10) + "-" + args[0].substring(3, 5) + "-" + args[0].substring(0, 2);
    			date2 = args[1].substring(6, 10) + "-" + args[1].substring(3, 5) + "-" + args[1].substring(0, 2);
    		}
    		profit = ((Manager) system.getCurrentUser()).computeTotalProfit(system, LocalDate.parse(date1), LocalDate.parse(date2));
    	} else {
    		profit = ((Manager) system.getCurrentUser()).computeTotalProfit(system, LocalDate.MIN, LocalDate.MAX);
    	}
    	
    	print("Total profit: " + profit + " euros.");
    	
    }

    /**
     * Runs a test scenario based on the provided arguments.
     *
     * @param args the arguments for running the test, such as test scenario file
     */
    public static void runTest(String... args) {
    	if (args.length > 1) {
            print("Usage: RUNTEST <testScenarioFile> - execute the list of CLUI commands contained in the testScenario file passed as argument.");
            return;
        }
    	
    	// Reading commands
    	FileReader file = null;
    	BufferedReader reader = null;
    	ArrayList<String> commands = new ArrayList<String>();
    	
    	try {
    		file = new FileReader(args[0]);     // a FileReader for reading byte−by−byte
    		reader = new BufferedReader(file); // wrapping a FileReader into a BufferedReader for reading line−by−line
    		
    		String line = "";
    		while ((line = reader.readLine()) != null) { // read the file line−by−line
    			commands.add(line);
    		}

    	} catch (IOException e) {
    		print("Error reading the file " + args[0] + " : "+ e.getMessage());
    		return;
    	}
    	
    	if (reader != null) {
			try {reader.close();}
			catch (IOException e) {// Ignore issues }
			}
		}
    	
    	// Executing commands
    	System.out.println("EXECUTING TEST FILE " + args[0] + " ...");
    	for (String command : commands) {
    		// If it's a comment or a empty line, goes to the next line
    		if (command.length() == 0) continue;
    		if (command.substring(0, 1).equals("\n")) continue;
    		if (command.substring(0, 2).equals("//")) continue;
    		
    		// Printing and handling command
    		System.out.println("\n> " + command);
    		if(command.toUpperCase().equals("EXIT")) {
    			break;
    		} else {
    			handleCommand(command);
    		}
    	}
    	
    	// Printing final message
    	System.out.println("\nEND OF EXECUTION OF " + args[0] + " TEST FILE.");
    }
    
}