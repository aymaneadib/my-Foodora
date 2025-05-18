package fidelity;

import order.Order;
import users.Customer;

public class PointCard extends FidelityCard { 

    private int points;
    private double moneySpent;
    private boolean nextOrderDiscount;

    public PointCard(Customer owner) {
        super(owner);
        this.points = 0;
        this.moneySpent = 0;
        this.nextOrderDiscount = false;
        type = FidelityCardType.PointCard;
    }

    public void addMoneySpent(double amount) {
        double relevantAmount = moneySpent%1000;
        this.moneySpent += amount;
        points = (int) ((relevantAmount+moneySpent) / 10); // Earn 1 point for every 10 spent
        if (points >= 100) {
            nextOrderDiscount = true; // Next order is eligible for discount
            points -= 100; // Reset points after using them
        }
    }

    public int getPoints() {
        return points;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public boolean isNextOrderDiscount() {
        return nextOrderDiscount;
    }

    public void setNextOrderDiscount(boolean nextOrderDiscount) {
        this.nextOrderDiscount = nextOrderDiscount;
    }

    @Override
    public double orderReduction(Order order) {
        if (nextOrderDiscount) {
            nextOrderDiscount = false; // Reset discount after use
            return order.getPrice() * 0.1; // 10% discount on the next order
        }
        return 0; // No discount if not eligible
    }

    @Override
    public double getFinalPrice(Order order) {
        double discount = orderReduction(order);
        return order.getPrice() - discount; // Final price after applying the discount
    }

    @Override
    public String toString() {
        return "PointCard{";
    }

}
