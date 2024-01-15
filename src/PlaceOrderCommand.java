public class PlaceOrderCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Creating a new order...");
        Order order = new Order("ORD-" + System.currentTimeMillis());
        OrderRepository.getInstance().addOrder(order);

        // Prompt the user to create ice creams until they say "No"
        while (true) {
            order.createOrderItem();
            System.out.print("Do you want to add another item? (Yes/No): ");
            String response = App.getInstance().getScanner().nextLine().trim().toLowerCase();
            if (response.equals("no")) {
                break;
            }
        }

        // Display order details
        System.out.println("\nOrder Summary:");
        order.printOrderDetails();

        // Confirm the order
        System.out.print("Confirm the order? (Yes/No): ");
        String confirmation = App.getInstance().getScanner().nextLine().trim().toLowerCase();
        if (confirmation.equals("yes")) {
            OrderCreatedState state = (OrderCreatedState) order.getState();
            state.setIsCancelled(false);
            order.process();
        } else {
            OrderCreatedState state = (OrderCreatedState) order.getState();
            state.setIsCancelled(true);
            order.process();
        }
    }

}
