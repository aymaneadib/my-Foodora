package fidelity;

import users.Customer;
import order.Order;

public class BasicCard extends FidelityCard {

    public BasicCard(Customer owner) {
        super(owner);
        type = FidelityCardType.BasicCard;
    }

    @Override
    public double orderReduction(Order order) {
        return 0; // No reduction for basic card
    }

    @Override
    public double getFinalPrice(Order order) {
        return order.getPrice(); // No reduction for basic card
    }

    @Override
    public String toString() {
        return "BasicCard";
    }
}
