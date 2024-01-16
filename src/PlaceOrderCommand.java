public class PlaceOrderCommand implements Command{

    private String username;

    public PlaceOrderCommand (String username){
        this.username = username;
    }

    @Override
    public void execute() {
        System.out.println("Creating a new order...");
        Order order = new Order("ORD-" + System.currentTimeMillis(), this.username);
        OrderRepository.getInstance().addOrder(order);

        // Prompt the user to create items until they say "No"
        while (true) {
            order.createOrderItem();
            System.out.print("Do you want to add another item? (Yes/No): ");
            String response = CustomerApp.getInstance().getScanner().nextLine().trim().toLowerCase();
            if (response.equals("no")) {
                break;
            }
        }

        // Display order details
        System.out.println("\nOrder Summary:");
        order.printOrderDetails();

        // Confirm the order
        System.out.print("Confirm the order? (Yes/No): ");
        String confirmationResponse = CustomerApp.getInstance().getScanner().nextLine().trim().toLowerCase();
        if (confirmationResponse.equals("yes")) {
            OrderCreatedState state = (OrderCreatedState) order.getState();
            state.setIsCancelled(false);
            order.process();
        } else {
            OrderCreatedState state = (OrderCreatedState) order.getState();
            state.setIsCancelled(true);
            order.process();
            return;
        }

        //Customize the order
        System.out.println("Do you want to customize the order? (Yes/No): ");
        String customizationResponse = CustomerApp.getInstance().getScanner().nextLine().trim().toLowerCase();
        if(customizationResponse.equals("yes")){
            OrderPlacedState state = (OrderPlacedState) order.getState();
            state.setIsCustomizable(true);
            Command customizeOrderCommand = new CustomizeOrderCommand(order);
            customizeOrderCommand.execute();
            order.process();
            System.out.println("New Order Details: ");
            order.printOrderDetails();
            order.process();
        }else{
            OrderPlacedState state = (OrderPlacedState) order.getState();
            state.setIsCustomizable(false);
            order.process();
        }

        //Save Order as Favorite
        Command saveFavoriteCommand = new SaveFavoriteOrderCommand(order, username);
        saveFavoriteCommand.execute();

        //Choose Delivery Method
        Command setDeliveryOptionCommand = new SetDeliveryOptionCommand(order);
        setDeliveryOptionCommand.execute();

        // Select Promotion
        Command selectPromotionCommand = new SelectPromotionCommand(order);
        selectPromotionCommand.execute();

        // Make Payment
        Command selectPaymentMethodCommand = new SelectPaymentMethodCommand(order);
        selectPaymentMethodCommand.execute();

        //Final Data
        System.out.println("\n");
        order.printOrderDetails();
    }

}
