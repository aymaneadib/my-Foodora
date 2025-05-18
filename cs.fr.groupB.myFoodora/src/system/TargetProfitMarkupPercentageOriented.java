package system;

import java.util.Set;

import order.Order;

public class TargetProfitMarkupPercentageOriented implements ProfitStrategy {

	@Override
	public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
		double deliveryCost = profitData.getDeliveryCost();
		double serviceFee = profitData.getServiceFee();
		int numberOfOrders = lastMonthOrders.size();
		double averagePriceOfOrder = getAveragePriceOfOrder(lastMonthOrders);
		
		double newmarkupPercentage = (targetProfit/numberOfOrders - serviceFee + deliveryCost)/averagePriceOfOrder;
		ProfitData newProfitData = new ProfitData(newmarkupPercentage, serviceFee, deliveryCost);
		
		return newProfitData;
	}
	
	private double getAveragePriceOfOrder(Set<Order> lastMonthOrders) {
		double sum = 0;
		
		for(Order order : lastMonthOrders) {
			sum += order.getPrice();
		}
		
		return (sum/lastMonthOrders.size());
	}

}
