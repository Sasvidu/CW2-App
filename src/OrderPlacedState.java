public class OrderPlacedState implements OrderState{

    @Override
    public void process(Order order) {
        order.setState(new OrderInPreparationState());
        System.out.println("Order is being prepared.");
    }

    @Override
    public String getStateName() {
        return "Placed";
    }

}
