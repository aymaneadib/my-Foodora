package users;

import java.util.ArrayList;
import java.util.List;

import system.MyFoodora;

public class Manager extends Person {

    private static final List<String> availableOperations = new ArrayList<>(); 

    public Manager(String name, String surname, String username, String password) throws BadUserCreationException {
        super(name, surname, username, password);
    }

    public void addUser(User user, MyFoodora system) {
        system.addUser(user);
    }

    public void removeUser(User user, MyFoodora system) {
        system.removeUser(user);
    }

    public void setServiceFee(double fee, MyFoodora system) {
        system.setServiceFee(fee);
    }


    public static List<String> getAvailableOperations() {
        return availableOperations;
    }

    @Override
    public String toString() {
        return "Manager " + surname + " " +"( "+ id +" )";
    }
}
