## Overview
my-Foodora is a Java-based application designed to simulate a food ordering and delivery platform. The system allows customers to browse menus, place orders, and have meals delivered from various restaurants. It also provides management features for restaurant owners and system administrators.

## Features

- **User Roles**: Supports Customers, Restaurant Owners, Couriers, and Administrators.
- **Order Management**: Customers can create, modify, and track orders.
- **Menu Handling**: Restaurants can manage menus, meals, and special offers.
- **Delivery System**: Couriers are assigned orders and can update delivery status.
- **Promotions**: Supports discounts, loyalty programs, and special offers.
- **Administration**: Admins can manage users, view statistics, and configure system parameters.

## Project Structure

- `cs/fr/groupB/myFoodora/`
    - `core/`: Core business logic and main entities (User, Order, Meal, etc.)
    - `ui/`:  User interface components (if applicable)
    - `exceptions/`: Custom exception classes for error handling
    - `utils/`: Utility classes and helper functions
    - `test/`: Unit tests for core functionalities

## Getting Started

1. **Clone the repository**
     ```bash
     git clone <repository-url>
     ```
2. **Compile the project**
     ```bash
     javac -d bin src/cs/fr/groupB/myFoodora/**/*.java
     ```
3. **Run the application**
     ```bash
     java -cp bin cs.fr.groupB.myFoodora.Main
     ```

## Requirements

- Java 8 or higher

## Authors

- Aymane Chaoui Adib,   Group B
- Alisson Bonnato,      Group B

## License

This project is for educational purposes.