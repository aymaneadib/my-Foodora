package system;

import order.*;
import java.util.*;
public class MyFoodora {

    private HashSet<Order> orderHistory;

    public MyFoodora() {
        this.orderHistory = new HashSet<>();
    }

    public HashSet<Order> getOrderHistory() {
        return orderHistory;
    }
}
