package Order.States;

import Order.Order;

public interface OrderState {

    void process(Order order);

    String getStateName();

}
