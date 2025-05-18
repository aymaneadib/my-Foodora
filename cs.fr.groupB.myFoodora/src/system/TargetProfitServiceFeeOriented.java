package system;

import java.util.Set;

import order.Order;

public class TargetProfitServiceFeeOriented implements ProfitStrategy{

	@Override
	public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
		double markupPercentage = profitData.getMarkupPercentage();
		double deliveryCost = profitData.getDeliveryCost();
		int numberOfOrders = lastMonthOrders.size();
		double averagePriceOfOrder = getAveragePriceOfOrder(lastMonthOrders);
		
		double newServiceFee = targetProfit/numberOfOrders - averagePriceOfOrder*markupPercentage + deliveryCost;
		ProfitData newProfitData = new ProfitData(markupPercentage, newServiceFee, deliveryCost);
		
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
