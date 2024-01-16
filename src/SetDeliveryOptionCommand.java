import java.util.Scanner;

public class SetDeliveryOptionCommand implements Command {

    private Order order;

    public SetDeliveryOptionCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        Scanner scanner = CustomerApp.getInstance().getScanner();
        OrderInPreparationState state;

        try {
            state = (OrderInPreparationState) order.getState();
        } catch (Exception e) {
            throw e;
        }

        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Select delivery option (Pickup / Delivery): ");
            String deliveryOption = scanner.nextLine().trim().toLowerCase();

            switch (deliveryOption) {
                case "pickup":
                    state.setIsPickup(true);
                    isValidInput = true;
                    System.out.println("Pick up option selected\n\n");
                    break;
                case "delivery":
                    state.setIsPickup(false);
                    isValidInput = true;
                    System.out.println("Delivery option selected\n\n");
                    break;
                default:
                    System.out.println("Invalid delivery option. Please choose either pickup or delivery.");
            }
        }
    }

}
