package system;

/**
 * Represents the profit data for the MyFedoora system.
 * 
 * @author Alisson Bonatto
 */
public class ProfitData {
	
    private double markupPercentage;
    private double serviceFee;
    private double deliveryCost;
    
    /**
     * Constructs a ProfitData instance with specified values.
     * 
     * @param markupPercentage the markup percentage applied
     * @param serviceFee the service fee
     * @param deliveryCost the delivery cost
     */
	public ProfitData(double markupPercentage, double serviceFee, double deliveryCost) {
		this.markupPercentage = markupPercentage;
		this.serviceFee = serviceFee;
		this.deliveryCost = deliveryCost;
	}
	
	/**
	 * Constructs a ProfitData instance with default values (all zero).
	 */
	public ProfitData() {
		this.markupPercentage = 0;
		this.serviceFee = 0;
		this.deliveryCost = 0;
	}

	/**
	 * Returns the markup percentage.
	 * 
	 * @return the markup percentage
	 */
	public double getMarkupPercentage() {
		return markupPercentage;
	}

	/**
	 * Sets the markup percentage.
	 * 
	 * @param markupPercentage the markup percentage to set
	 */
	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}

	/**
	 * Returns the service fee.
	 * 
	 * @return the service fee
	 */
	public double getServiceFee() {
		return serviceFee;
	}

	/**
	 * Sets the service fee.
	 * 
	 * @param serviceFee the service fee to set
	 */
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * Returns the delivery cost.
	 * 
	 * @return the delivery cost
	 */
	public double getDeliveryCost() {
		return deliveryCost;
	}

	/**
	 * Sets the delivery cost.
	 * 
	 * @param deliveryCost the delivery cost to set
	 */
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	/**
	 * Returns a string representation of this ProfitData instance.
	 * 
	 * @return string describing markupPercentage, serviceFee, and deliveryCost
	 */
	@Override
	public String toString() {
		return "ProfitData : markupPercentage=" + markupPercentage + ", serviceFee=" + serviceFee + ", deliveryCost="
				+ deliveryCost;
	}

}
