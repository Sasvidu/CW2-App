package Application;

import Commands.*;
import Order.Order;
import java.util.*;

public class DeliveryApp implements App {

    private static DeliveryApp instance;

    private Scanner scanner;
    private String currentDeliverer;
    private String currentUser;
    private Map<String, List<Order>> delivererToOrderMap;

    private DeliveryApp() {
        System.out.println("Delivery App Started!\nInitializing...\n");
        this.scanner = new Scanner(System.in);
        this.delivererToOrderMap = new HashMap<>();
        System.out.println("\n\n\n");
    }

    public static synchronized DeliveryApp getInstance() {
        if (instance == null) {
            instance = new DeliveryApp();
        }
        return instance;
    }

    public void setCurrentDeliverer(String username) {
        this.currentDeliverer = username;
    }

    @Override
    public void run() {
        System.out.println("Welcome to the Delivery App!");
        System.out.println("Commands: Accept Delivery / Complete Delivery / Logout / Quit\n\n");

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "accept delivery":
                    Command acceptDeliveryCommand = new AcceptDeliveryCommand(currentDeliverer);
                    acceptDeliveryCommand.execute();
                    break;
                case "complete delivery":
                    Command completeDeliveryCommand = new CompleteDeliveryCommand(currentDeliverer);
                    completeDeliveryCommand.execute();
                    break;
                case "logout":
                    System.out.println("Logging out...");
                    return;
                case "quit":
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Please try again.\n");
                    break;
            }
        }
    }

    public void AddDeliveryMapEntry(Order order) {
        List<Order> ordersForDeliverer = delivererToOrderMap.getOrDefault(currentDeliverer, new ArrayList<>());
        ordersForDeliverer.add(order);
        delivererToOrderMap.put(currentDeliverer, ordersForDeliverer);
    }
    public Scanner getScanner() {
        return scanner;
    }

    public Map getDelivererToOrderMap(){
        return this.delivererToOrderMap;
    }

}
