public class ViewAllOrdersCommand implements Command{

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        orderRepository.printAllOrders();
    }

}
