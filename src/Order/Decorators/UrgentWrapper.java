package Order.Decorators;

import Order.Decorators.OrderDecorator;

public class UrgentWrapper implements OrderDecorator {

    private OrderDecorator wrappee;

    public UrgentWrapper(OrderDecorator wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double getCost() {
        return wrappee.getCost() + 300.0;
    }

}
