public class OrderCustomizedState implements OrderState{

    @Override
    public void process(Order order) {
        order.setState(new OrderInPreparationState());
    }

    @Override
    public String getStateName() {
        return "Placed and Customized";
    }

}
