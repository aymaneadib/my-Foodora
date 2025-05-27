package fidelity;

import user.*;

/**
 * Factory class for creating different types of Fidelity Cards.
 * This class provides a static method to create instances of different Fidelity Card types.
 * 
 * @author Aymane Adib
 */
public class FidelityCardFactory {

    /**
     * Creates a Fidelity Card based on the specified type.
     * 
     * @param type the type of Fidelity Card to create (e.g., "BasicCard", "PointCard", "LotteryCard")
     * @param owner the Customer who will own the Fidelity Card
     * @return an instance of the specified Fidelity Card type
     * @throws IllegalArgumentException if the specified type is not recognized
     */
    public static FidelityCard createFidelityCard(String type, Customer owner) {
        switch (type.toLowerCase()) {
            case "basiccard":
                return new BasicCard(owner);
            case "pointcard":
                return new PointCard(owner);
            case "lotterycard":
                return new LotteryCard(owner);
            default:
                throw new IllegalArgumentException("Unknown Fidelity Card Type: " + type);
        }
    }
}
