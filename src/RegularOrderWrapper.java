public class RegularOrderWrapper implements OrderDecorator {

    private Order order;

    public RegularOrderWrapper(Order order) {
        this.order = order;
    }

    @Override
    public double getCost() {
        return order.calculateTotalPrice();
    }

}
