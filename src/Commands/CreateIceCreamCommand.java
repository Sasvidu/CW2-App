package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Order.IceCream.*;
import Payments.*;
import APIs.*;
import java.util.Scanner;

public class CreateIceCreamCommand implements Command {

    private Order order;

    public CreateIceCreamCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        IceCreamDirector iceCreamDirector = new IceCreamDirector();
        IceCream iceCream = iceCreamDirector.createIceCream();

        if (iceCream != null) {
            Scanner scanner = CustomerApp.getInstance().getScanner();

            System.out.print("Enter the quantity of ice cream needed: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            // Add the created ice cream to the order
            order.addItem(iceCream, quantity);
            System.out.println("Ice Cream added to order: " + iceCream);
        }
    }

}
