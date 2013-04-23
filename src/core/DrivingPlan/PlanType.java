package core.DrivingPlan;

public enum PlanType {
    OCCASIONAL("Occasional Driving",50,0,0),
    FREQUENT("Frequent Driving",0,60,0.10),
    DAILY("Daily Driving",0,100,0.15);
    
    private PlanType(final String type, final int annualFees, 
            final int monthlyPay, final double discount) {
        this.typeStr = type;
        this.setAnnualFees(annualFees);
        this.setMonthlyPay(monthlyPay);
        this.discount = discount;
    }
    private final String typeStr; 
    private int annualFees, monthlyPay;
    private final double discount;
    
    @Override
    public String toString() {
        return typeStr;
    }
    
    public boolean equals(PlanType type) {
        if(type.equals(type.typeStr))
            return true;
        return false;
    }

    /**
     * @param annualFees the annualFees to set
     */
    public void setAnnualFees(int annualFees) {
        this.annualFees = annualFees;
    }

    /**
     * @return the annualFees
     */
    public int getAnnualFees() {
        return annualFees;
    }

    /**
     * @param monthlyPay the monthlyPay to set
     */
    public void setMonthlyPay(int monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    /**
     * @return the monthlyPay
     */
    public int getMonthlyPay() {
        return monthlyPay;
    }

    /**
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }
}
