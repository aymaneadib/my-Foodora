package system;

import java.util.Set;

import order.Order;

/**
 * Implementation of ProfitStrategy focused on adjusting the delivery cost
 * to achieve a target profit, considering markup percentage and service fee.
 * 
 * This strategy recalculates the delivery cost based on the average order price,
 * markup, service fee, and target profit distributed over the number of orders.
 * 
 * @author Alisson Bonatto
 */
public class TargetProfitDeliveryCostOriented implements ProfitStrategy{

    /**
     * Calculates new profit data by adjusting the delivery cost to meet the target profit.
     * It uses the markup percentage, service fee, number of orders, and average order price in the calculation.
     * 
     * @param profitData current profit data
     * @param lastMonthOrders set of orders from the last month
     * @param targetProfit desired profit target to achieve
     * @return updated ProfitData with adjusted delivery cost
     */
    @Override
    public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
        double markupPercentage = profitData.getMarkupPercentage();
        double serviceFee = profitData.getServiceFee();
        int numberOfOrders = lastMonthOrders.size();
        double averagePriceOfOrder = getAveragePriceOfOrder(lastMonthOrders);

        // Computing the delivery cost
        double newDeliveryCost = averagePriceOfOrder * markupPercentage + serviceFee - targetProfit / numberOfOrders;
        ProfitData newProfitData = new ProfitData(markupPercentage, serviceFee, newDeliveryCost);

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
