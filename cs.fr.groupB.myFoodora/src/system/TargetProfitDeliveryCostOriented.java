package system;

import java.util.Set;

import order.Order;

public class TargetProfitDeliveryCostOriented implements ProfitStrategy{

	@Override
	public ProfitData getProfitData(ProfitData profitData, Set<Order> lastMonthOrders, double targetProfit) {
		double markupPercentage = profitData.getMarkupPercentage();
		double serviceFee = profitData.getServiceFee();
		int numberOfOrders = lastMonthOrders.size();
		double averagePriceOfOrder = getAveragePriceOfOrder(lastMonthOrders);
		
		double newDeliveryCost = averagePriceOfOrder*markupPercentage + serviceFee - targetProfit/numberOfOrders;
		ProfitData newProfitData = new ProfitData(markupPercentage, serviceFee, newDeliveryCost);
		
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
