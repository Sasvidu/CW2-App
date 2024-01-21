package Order.States;

import Order.Order;

public class OrderInDeliveryState implements OrderState {

    private String deliverer;

    @Override
    public void process(Order order) {
        if(order.getIsPaid()){
            order.setState(new OrderCompletedState());
        }else{
            throw new IllegalStateException("Order.Order needs to be paid for before completion");
        }
    }

    @Override
    public String getStateName() {
        return "Being Delivered...";
    }

    public void setDeliverer(String delivererUsername){
        this.deliverer = delivererUsername;
    }

}
