public class OrderOutForDeliveryState implements OrderState {

    @Override
    public void process(Order order) {

    }

    @Override
    public String getStateName() {
        return "Out for Delivery";
    }

}
