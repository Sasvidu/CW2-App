package Order.Handlers;

import Order.Order;

public abstract class BaseWrapperHandler implements WrapperHandler{

    private WrapperHandler nextHandler;

    @Override
    public void setNextHandler(WrapperHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected void handleNext(Order order) {
        if (nextHandler != null) {
            nextHandler.handleRequest(order);
        }
    }

}
