package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Order.IceCream.*;
import Payments.*;
import APIs.*;
import java.util.List;
import java.util.Scanner;

public class SelectPaymentMethodCommand implements Command {

    private Order order;

    public SelectPaymentMethodCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        Scanner scanner = CustomerApp.getInstance().getScanner();
        System.out.println("");

        // Display available payment methods
        System.out.println("Available Payment Methods:");
        List<PaymentStrategy> paymentStrategies = PaymentRepository.getInstance().getAllPaymentStrategies();
        for (int i = 0; i < paymentStrategies.size(); i++) {
            PaymentStrategy strategy = paymentStrategies.get(i);
            System.out.println((i + 1) + ". " + strategy.getClass().getSimpleName());
        }

        // Prompt user to select a payment method
        int selectedPaymentIndex;
        do {
            System.out.print("Select a payment method by entering its number (1-" + paymentStrategies.size() + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            selectedPaymentIndex = scanner.nextInt();
        } while (selectedPaymentIndex < 1 || selectedPaymentIndex > paymentStrategies.size());

        // Set the selected payment strategy to the order
        PaymentStrategy selectedPaymentStrategy = paymentStrategies.get(selectedPaymentIndex - 1);
        PaymentContext paymentContext = new PaymentContext();
        paymentContext.setPaymentStrategy(selectedPaymentStrategy);
        order.pay(paymentContext);

        System.out.println("Payment successful using " + selectedPaymentStrategy.getClass().getSimpleName());
        System.out.println("Final Amount after discount: $" + order.getWrapper().getCost());
    }

}
