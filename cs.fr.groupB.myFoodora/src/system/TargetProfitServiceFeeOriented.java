package system;

import java.util.Set;

import order.Order;

/**
 * Implementation of ProfitStrategy focused on adjusting the service fee
 * to achieve a target profit, considering markup percentage and delivery cost.
 * 
 * This strategy recalculates the service fee based on the target profit,
 * number of orders, markup percentage, delivery cost, and average price of orders.
 * 
 * @author Alisson Bonatto
 */
public class TargetProfitServiceFeeOriented implements ProfitStrategy {

    /**
     * Calculates new profit data by adjusting the service fee to meet the target profit.
     * It uses the markup percentage, delivery cost, number of orders, and average order price in the calculation.
     * 
     * @param profitData current profit data
     * @param lastMonthOrders set of orders from the last month
     * @param targetProfit desired profit target to achieve
     * @return updated ProfitData with adjusted service fee
     */
    @Override
    public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
        double markupPercentage = profitData.getMarkupPercentage();
        double deliveryCost = profitData.getDeliveryCost();
        int numberOfOrders = lastMonthOrders.size();
        double averagePriceOfOrder = getAveragePriceOfOrder(lastMonthOrders);

        // Computing the service fee
        double newServiceFee = targetProfit / numberOfOrders - averagePriceOfOrder * markupPercentage + deliveryCost;
        ProfitData newProfitData = new ProfitData(markupPercentage, newServiceFee, deliveryCost);

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

        for (Order order : lastMonthOrders) {
            sum += order.getPrice();
        }

        return sum / lastMonthOrders.size();
    }

}
