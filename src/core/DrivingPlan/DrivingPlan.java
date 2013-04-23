package core.DrivingPlan;

public class DrivingPlan {
    PlanType planType;
    
    public DrivingPlan(PlanType planType) {
        this.planType = planType;
    }
    
    public void getPlanFromType(String type) {
        this.planType = PlanType.valueOf(type);
    }

    /**
     * @return the planType
     */
    public PlanType getPlanType() {
        return planType;
    }

    /**
     * @param planType the planType to set
     */
    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }
}
