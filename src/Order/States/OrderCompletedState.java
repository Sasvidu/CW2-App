package Order.States;

import Order.Order;

public class OrderCompletedState implements OrderState {

    @Override
    public void process(Order order) {
        System.out.println("Order.Order Already Completed! " + order.getOrderId());
    }

    @Override
    public String getStateName() {
        return "Completed";
    }

}
