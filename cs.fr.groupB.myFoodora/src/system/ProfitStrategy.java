package system;

import java.util.Set;

import order.Order;

public interface ProfitStrategy {
	
	public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders,double targetProfit);

}
