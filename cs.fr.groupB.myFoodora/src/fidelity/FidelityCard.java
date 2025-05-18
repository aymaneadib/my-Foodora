package fidelity;
import order.Order;
import users.Customer;
/**
 * Abstract class representing a fidelity card for a customer.
 * Provides methods to calculate order reductions and final prices.
 * 
 * @author Aymane Adib
 */
public  abstract class FidelityCard {

    protected Customer owner;
    protected FidelityCardType type;


    /**
     * Constructor for FidelityCard.
     * @param owner the customer who owns the fidelity card
     */
    public FidelityCard(Customer owner) {
        this.owner = owner;
    }

    /**
     * Returns the owner of the fidelity card.
     * @return the customer who owns the fidelity card
     */
    public Customer getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the fidelity card.
     * @param owner the customer who owns the fidelity card
     */
    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    /**
     * Returns the type of the fidelity card.
     * @return the type of the fidelity card
     */
    public FidelityCardType getType() {
        return type;
    }
    
    /**
     * Sets the type of the fidelity card.
     * @param type the type of the fidelity card
     */
    public void setType(FidelityCardType type) {
        this.type = type;
    }
    
    public abstract double orderReduction(Order order);
    public abstract double getFinalPrice(Order order);
}
