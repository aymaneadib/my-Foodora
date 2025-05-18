package fidelity;

import users.*;

public class FidelityCardFactory {
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
