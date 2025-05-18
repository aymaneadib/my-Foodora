package system;

public class ProfitData {
	
    private double markupPercentage;
    private double serviceFee;
    private double deliveryCost;
    
	public ProfitData(double markupPercentage, double serviceFee, double deliveryCost) {
		this.markupPercentage = markupPercentage;
		this.serviceFee = serviceFee;
		this.deliveryCost = deliveryCost;
	}
	
	public ProfitData() {
		this.markupPercentage = 0;
		this.serviceFee = 0;
		this.deliveryCost = 0;
	}

	public double getMarkupPercentage() {
		return markupPercentage;
	}

	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	@Override
	public String toString() {
		return "ProfitData : markupPercentage=" + markupPercentage + ", serviceFee=" + serviceFee + ", deliveryCost="
				+ deliveryCost;
	}

}
