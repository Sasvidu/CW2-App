public class CustomizeOrderCommand implements Command{

    private Order order;

    public CustomizeOrderCommand(Order order){
        this.order = order;
    }

    @Override
    public void execute() {
        // Create handlers
        WrapperHandler giftHandler = new GiftWrapperHandler();
        WrapperHandler urgentHandler = new UrgentWrapperHandler();

        // Set up the chain
        giftHandler.setNextHandler(urgentHandler);

        // Add Wrappers
        giftHandler.handleRequest(order);
    }
}
