public class OrderInPreparationState implements OrderState{

    private String isPickup;

    enum isPickupValues{
        YES,
        NO
    }

    public OrderInPreparationState(){
        this.isPickup = null;
    }

    public void setIsPickup(boolean isPickup){
        if(isPickup){
            this.isPickup = isPickupValues.YES.toString();
        }else{
            this.isPickup = isPickupValues.NO.toString();
        }
    }

    @Override
    public void process(Order order) {
        if(this.isPickup == null){
            throw new IllegalArgumentException("Please select between pick up or delivery option.");
        }else if(this.isPickup.equals(isPickupValues.YES.toString())){
            order.setState(new OrderOutForPickupState());
        }else if(this.isPickup.equals(isPickupValues.NO.toString())){
            order.setState(new OrderOutForDeliveryState());
        }else{
            this.isPickup = null;
            throw new IllegalArgumentException("Something went wrong, please try again.");
        }
    }

    @Override
    public String getStateName() {
        return "Being Prepared";
    }

    public String getIsPickup(){ return isPickup; }

}
