import java.util.Scanner;

public class AdminApp implements App{

    private static AdminApp instance;

    private OrderRepository orderRepository;
    private Scanner scanner;

    private AdminApp() {
        System.out.println("Admin App Started!\nInitializing...\n");
        this.orderRepository = OrderRepository.getInstance();
        System.out.println("\n\n\n");
    }

    public static synchronized AdminApp getInstance() {
        if (instance == null) {
            instance = new AdminApp();
        }
        return instance;
    }

    @Override
    public void run() {
        System.out.println("Welcome to the Admin Panel!");
        System.out.println("Commands: Dispatch Order / Add Seasonal Promotion / View All Orders / Logout / Quit\n\n");

        while (true) {
            this.scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "dispatch order":
                    Command dispatchOrderCommand = new CompleteOrderPreparationCommand();
                    dispatchOrderCommand.execute();
                    break;
                case "add seasonal promotion":
                    Command addSeasonalPromotionCommand = new AddSeasonalPromotionCommand();
                    addSeasonalPromotionCommand.execute();
                    break;
                case "view all orders":
                    Command viewAllOrdersCommand = new ViewAllOrdersCommand();
                    viewAllOrdersCommand.execute();
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

    public Scanner getScanner() {
        return scanner;
    }

}
