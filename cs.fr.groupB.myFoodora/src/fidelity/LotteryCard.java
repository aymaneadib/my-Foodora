package fidelity;

import order.Order;
import user.Customer;


/**
 * Class representing a LotteryCard, which is a type of FidelityCard.
 * This card allows the owner to participate in a lottery offering a free order in case of success.
 * 
 * @author Aymane Adib
 */
public class LotteryCard extends FidelityCard {

    private double probability; // Probability of winning the lottery

    /**
     * Constructor for LotteryCard.
     * Initializes the card with a default probability of winning.
     *
     * @param owner the customer who owns the LotteryCard
     */
    public LotteryCard(Customer owner) {
        super(owner);
        this.probability = 0.005; // Default probability
    }

    /**
     * Constructor for LotteryCard with a specified probability.
     *
     * @param owner the customer who owns the LotteryCard
     * @param probability the probability of winning the lottery
     */
    public LotteryCard(Customer owner, double probability) {
        super(owner);
        this.probability = probability;
    }

    /**
     * Returns the probability of winning the lottery.
     *
     * @return the probability of winning
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Sets the probability of winning the lottery.
     *
     * @param probability the new probability of winning
     */
    public void setProbability(double probability) {
        this.probability = probability;
    }

    /**
     * Calculates the order reduction based on the lottery probability.
     * If the lottery is won, the entire order price is reduced.
     *
     * @param order the order for which the reduction is calculated
     * @return the reduction amount
     */
    @Override
    public double orderReduction(Order order) {
        // Simulate a lottery draw
        if (Math.random() < probability) {
            return order.getPrice(); // 10% discount if the lottery is won
        }
        return 0; // No discount if the lottery is not won
    }

    /**
     * Calculates the final price for the given order using the LotteryCard.
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
     * Returns a string representation of the LotteryCard object.
     *
     * @return a string "LotteryCard" representing this card type.
     */
    @Override
    public String toString() {
        return "LotteryCard";
    }

}
