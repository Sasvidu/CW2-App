public class OrderCreatedState implements OrderState {

    private String isCancelled;

    enum isCancelledValues{
        YES,
        NO
    }

    public OrderCreatedState() {
        this.isCancelled = null;
    }

    public void setIsCancelled(boolean isCancelled) {
        if (isCancelled) {
            this.isCancelled = isCancelledValues.YES.toString();
        } else {
            this.isCancelled = isCancelledValues.NO.toString();
        }
    }

    @Override
    public void process(Order order) {
        if(this.isCancelled == null){
            throw new IllegalArgumentException("Please confirm the order.\n");
        }else if(this.isCancelled.equals(isCancelledValues.YES.toString())){
            order.setState(new OrderCancelledState());
        }else if(this.isCancelled.equals(isCancelledValues.NO.toString())){
            order.setState(new OrderPlacedState());
        }else{
            this.isCancelled = null;
            throw new IllegalArgumentException("Something went wrong, please try again.\n\n");
        }
    }

    @Override
    public String getStateName() {
        return "Created";
    }

}
