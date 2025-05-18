package system;

import java.util.Set;

import order.Order;

/**
 * Interface defining the strategy for calculating profit data
 * based on previous orders a target profit and a the policy.
 * 
 * Implementations should provide the logic to update or calculate
 * ProfitData according to their specific policy.
 * 
 * @author Alisson Bonatto
 */
public interface ProfitStrategy {
	
	/**
	 * Calculates and returns updated ProfitData based on given input data,
	 * previous orders, and a target profit.
	 * 
	 * @param profitData the initial profit data
	 * @param lastMonthOrders a set of orders from the last month for analysis
	 * @param targetProfit the desired profit target to reach or consider
	 * @return updated ProfitData reflecting the strategy's calculations
	 */
	public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit);

}
