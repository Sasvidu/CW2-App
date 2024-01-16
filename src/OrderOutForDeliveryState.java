public class OrderOutForDeliveryState implements OrderState {

    @Override
    public void process(Order order) {
        order.setState(new OrderInDeliveryState());
    }

    @Override
    public String getStateName() {
        return "Out for Delivery";
    }

}
