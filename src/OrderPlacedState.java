public class OrderPlacedState implements OrderState{

    private String isCustomizable;

    enum isCustomizableValues{
        YES,
        NO
    }

    public OrderPlacedState(){
        this.isCustomizable = isCustomizableValues.NO.toString();
    }

    public void setIsCustomizable(boolean isCustomizable) {
        if (isCustomizable) {
            this.isCustomizable = OrderCreatedState.isCancelledValues.YES.toString();
        } else {
            this.isCustomizable = OrderCreatedState.isCancelledValues.NO.toString();
        }
    }

    @Override
    public void process(Order order) {
        if(this.isCustomizable == null){
            throw new IllegalArgumentException("Please select whether customization is required for the order.\n");
        }else if(this.isCustomizable.equals(OrderCreatedState.isCancelledValues.YES.toString())){
            order.setState(new OrderCustomizedState());
        }else if(this.isCustomizable.equals(OrderCreatedState.isCancelledValues.NO.toString())){
            order.setState(new OrderInPreparationState());
        }else{
            this.isCustomizable = null;
            throw new IllegalArgumentException("Something went wrong, please try again.\n\n");
        }
    }

    @Override
    public String getStateName() {
        return "Placed";
    }

}
