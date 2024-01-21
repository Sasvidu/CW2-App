package Order.Handlers;

import Order.Order;

public interface WrapperHandler {

    void handleRequest(Order order);
    void setNextHandler(WrapperHandler nextHandler);

}
