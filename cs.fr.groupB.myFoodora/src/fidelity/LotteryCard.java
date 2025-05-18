package fidelity;

import order.Order;
import users.Customer;

public class LotteryCard extends FidelityCard {

    private double probability;

    public LotteryCard(Customer owner) {
        super(owner);
        this.probability = 0.005; // Default probability
    }

    public LotteryCard(Customer owner, double probability) {
        super(owner);
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public double orderReduction(Order order) {
        // Simulate a lottery draw
        if (Math.random() < probability) {
            return order.getPrice(); // 10% discount if the lottery is won
        }
        return 0; // No discount if the lottery is not won
    }

    @Override
    public double getFinalPrice(Order order) {
        double discount = orderReduction(order);
        return order.getPrice() - discount; // Final price after applying the discount
    }

    @Override
    public String toString() {
        return "LotteryCard";
    }

}
