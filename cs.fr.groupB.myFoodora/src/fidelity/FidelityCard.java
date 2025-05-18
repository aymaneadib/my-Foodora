package fidelity;
import order.Order;
import users.Customer;

public  abstract class FidelityCard {

    protected Customer owner;
    protected FidelityCardType type;


    public FidelityCard(Customer owner) {
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public FidelityCardType getType() {
        return type;
    }

    public void setType(FidelityCardType type) {
        this.type = type;
    }

    public abstract double orderReduction(Order order);
    public abstract double getFinalPrice(Order order);
}
