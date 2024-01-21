package Order.States;

import Order.Order;

public class OrderOutForPickupState implements OrderState {

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
        return "Out for Pickup";
    }

}
