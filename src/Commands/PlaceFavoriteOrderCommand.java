package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Order.IceCream.*;
import Payments.*;
import APIs.*;
import java.util.Scanner;
import java.util.Map;

public class PlaceFavoriteOrderCommand implements Command {

    private String username;

    public PlaceFavoriteOrderCommand(String username) {
        this.username = username;
    }

    @Override
    public void execute() {
        CustomerApp customerApp = CustomerApp.getInstance();
        Map<String, Order> favoriteOrderMap = customerApp.getFavoriteOrderMap();
        System.out.println("Ordering your favorite...");
        String favoriteOrderId = "ORD-" + System.currentTimeMillis();


        if (favoriteOrderMap.containsKey(username) && favoriteOrderMap.get(username) != null) {
            Order originalOrder = favoriteOrderMap.get(username);
            Order clonedOrder = new Order(favoriteOrderId, this.username, originalOrder);
            OrderRepository.getInstance().addOrder(clonedOrder);

            // Display cloned order details
            System.out.println("\nOrder.Order Summary:");
            clonedOrder.printOrderDetails();

            // Confirm the cloned order
            System.out.print("Confirm the order? (Yes/No): ");
            String confirmationResponse = customerApp.getScanner().nextLine().trim().toLowerCase();

            if (confirmationResponse.equals("yes")) {
                OrderCreatedState state = (OrderCreatedState) clonedOrder.getState();
                state.setIsCancelled(false);
                clonedOrder.process();
            } else {
                OrderCreatedState state = (OrderCreatedState) clonedOrder.getState();
                state.setIsCancelled(true);
                clonedOrder.process();
                return;
            }

            // Customize the cloned order
            System.out.println("Do you want to customize the order? (Yes/No): ");
            String customizationResponse = customerApp.getScanner().nextLine().trim().toLowerCase();

            if (customizationResponse.equals("yes")) {
                OrderPlacedState state = (OrderPlacedState) clonedOrder.getState();
                state.setIsCustomizable(true);
                Command customizeOrderCommand = new CustomizeOrderCommand(clonedOrder);
                customizeOrderCommand.execute();
                clonedOrder.process();
                System.out.println("New Order.Order Details: ");
                clonedOrder.printOrderDetails();
                clonedOrder.process();
            } else {
                OrderPlacedState state = (OrderPlacedState) clonedOrder.getState();
                state.setIsCustomizable(false);
                clonedOrder.process();
            }

            //Choose Delivery Method
            Command setDeliveryOptionCommand = new SetDeliveryOptionCommand(clonedOrder);
            setDeliveryOptionCommand.execute();

            // Select Promotion
            Command selectPromotionCommand = new SelectPromotionCommand(clonedOrder);
            selectPromotionCommand.execute();

            // Make Payment
            Command selectPaymentMethodCommand = new SelectPaymentMethodCommand(clonedOrder);
            selectPaymentMethodCommand.execute();

            //Final Data
            System.out.println("\n");
            clonedOrder.printOrderDetails();
        } else {
            System.out.println("No favorite order found for the specified username.\n\n");
        }
    }


}
