package fidelity;

import order.Order;
import users.Customer;

/**
 * Class representing a PointCard, which is a type of FidelityCard.
 * This card allows the owner to earn points based on their spending,
 * and provides a discount on the next order if enough points are accumulated.
 * 
 * @author Aymane Adib
 */
public class PointCard extends FidelityCard { 

    private int points; // Number of points accumulated
    private double moneySpent; // Total money spent by the owner
    private boolean nextOrderDiscount; // Indicates if the next order is eligible for a discount

    /**
     * Constructor for PointCard.
     * Initializes the card with zero points and money spent.
     *
     * @param owner the customer who owns the PointCard
     */
    public PointCard(Customer owner) {
        super(owner);
        this.points = 0;
        this.moneySpent = 0;
        this.nextOrderDiscount = false;
        type = FidelityCardType.PointCard;
    }

    /**
     * Adds the specified amount to the total money spent.
     * Calculates points based on the money spent and checks if a discount is available for the next order.
     *
     * @param amount the amount to add to money spent
     */
    public void addMoneySpent(double amount) {
        double relevantAmount = moneySpent%1000; 
        this.moneySpent += amount; 
        points = (int) ((relevantAmount+moneySpent) / 10); // Earn 1 point for every 10 spent
        if (points >= 100) {
            nextOrderDiscount = true; // Next order is eligible for discount
            points -= 100; // Reset points after using them
        }
    }

    /**
     * Returns the number of points accumulated on the card.
     *
     * @return the number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the total money spent by the owner of the card.
     *
     * @return the total money spent
     */
    public double getMoneySpent() {
        return moneySpent;
    }

    /**
     * Returns whether the next order is eligible for a discount.
     *
     * @return true if the next order is eligible for a discount, false otherwise
     */
    public boolean isNextOrderDiscount() {
        return nextOrderDiscount;
    }

    /**
     * Sets the eligibility for the next order discount.
     *
     * @param nextOrderDiscount true if the next order is eligible for a discount, false otherwise
     */
    public void setNextOrderDiscount(boolean nextOrderDiscount) {
        this.nextOrderDiscount = nextOrderDiscount;
    }

    /**
     * Returns the type of the card.
     *
     * @return the type of the card
     */
    @Override
    public double orderReduction(Order order) {
        if (nextOrderDiscount) {
            nextOrderDiscount = false; // Reset discount after use
            return order.getPrice() * 0.1; // 10% discount on the next order
        }
        return 0; // No discount if not eligible
    }

    /**
     * Calculates the final price for the given order using the PointCard.
     * The final price is the original order price minus any reductions.
     *
     * @param order the order for which the final price is calculated
     * @return the final price after applying any reductions
     */
    @Override
    public double getFinalPrice(Order order) {
        double discount = orderReduction(order);
        return order.getPrice() - discount; // Final price after applying the discount
    }

    /**
     * Returns a string representation of the PointCard object.
     *
     * @return a string "PointCard" representing this card type.
     */
    @Override
    public String toString() {
        return "PointCard";
    }

}
