public class OrderCancelledState implements OrderState{

    @Override
    public void process(Order order) {
        System.out.println("Order Cancelled.");
    }

    @Override
    public String getStateName() {
        return "Cancelled";
    }

}
