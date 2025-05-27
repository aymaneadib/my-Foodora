package fidelity;

import order.Order;
import user.Customer;

/**
 * Represents a basic fidelity card for a customer in the Foodora system.
 * <p>
 * The BasicCard does not provide any reduction or special benefits on orders.
 * It serves as the default fidelity card type for customers.
 * </p>
 *
 * @author Aymane Adib
 */
public class BasicCard extends FidelityCard {

    public BasicCard(Customer owner) {
        super(owner);
        type = FidelityCardType.BasicCard;
    }

    /**
     * Returns the reduction applied to the order by the BasicCard.
     * Since BasicCard offers no reduction, this always returns 0.
     *
     * @param order the order for which the reduction is calculated
     * @return 0, as no reduction is applied
     */
    @Override
    public double orderReduction(Order order) {
        return 0; // No reduction for basic card
    }

    /**
     * Calculates the final price for the given order using the BasicCard.
     * Since BasicCard offers no reduction, it simply returns the original order price.
     *
     * @param order the order for which the final price is calculated
     * @return the original price of the order
     */
    @Override
    public double getFinalPrice(Order order) {
        return order.getPrice(); // No reduction for basic card
    }

    /**
     * Returns a string representation of the BasicCard object.
     *
     * @return a string "BasicCard" representing this card type.
     */
    @Override
    public String toString() {
        return "BasicCard";
    }
}
