package Order.Handlers;

import Application.CustomerApp;
import Order.Decorators.UrgentWrapper;
import Order.Order;

public class UrgentWrapperHandler extends BaseWrapperHandler {

    @Override
    public void handleRequest(Order order) {
        System.out.print("Do you want urgent delivery? (Yes/No): ");
        String response = CustomerApp.getInstance().getScanner().nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            order.addWrapper(new UrgentWrapper(order.getWrapper()));
        }

        handleNext(order);
    }

}
