package system;

import java.util.Set;

import order.Order;

/**
 * Implementation of ProfitStrategy focused on adjusting the markup percentage
 * to achieve a target profit, considering delivery cost and service fee.
 * 
 * This strategy recalculates the markup percentage based on the target profit,
 * number of orders, service fee, delivery cost, and average price of orders.
 * 
 * @author Alisson Bonatto
 */
public class TargetProfitMarkupPercentageOriented implements ProfitStrategy {

    /**
     * Calculates new profit data by adjusting the markup percentage to meet the target profit.
     * It uses the delivery cost, service fee, number of orders, and average order price in the calculation.
     * 
     * @param profitData current profit data
     * @param lastMonthOrders set of orders from the last month
     * @param targetProfit desired profit target to achieve
     * @return updated ProfitData with adjusted markup percentage
     */
    @Override
    public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
        double deliveryCost = profitData.getDeliveryCost();
        double serviceFee = profitData.getServiceFee();
        int numberOfOrders = lastMonthOrders.size();
        double averagePriceOfOrder = getAveragePriceOfOrder(lastMonthOrders);

        // Computing the markup percentage
        double newMarkupPercentage = (targetProfit / numberOfOrders - serviceFee + deliveryCost) / averagePriceOfOrder;
        ProfitData newProfitData = new ProfitData(newMarkupPercentage, serviceFee, deliveryCost);

        return newProfitData;
    }

    /**
     * Calculates the average price of the orders in the given set.
     * 
     * @param lastMonthOrders the set of orders to calculate the average from
     * @return the average price of orders, or 0 if the set is empty
     */
    private double getAveragePriceOfOrder(Set<Order> lastMonthOrders) {
        if (lastMonthOrders.isEmpty()) {
            return 0;
        }
        double sum = 0;

        for(Order order : lastMonthOrders) {
            sum += order.getPrice();
        }

        return sum / lastMonthOrders.size();
    }

}
