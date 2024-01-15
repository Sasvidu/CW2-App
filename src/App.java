import java.util.Scanner;

public class App {

    private static App instance;

    private DummyData dummyData;
    private FlavorRepository flavorRepository;
    private ToppingRepository toppingRepository;
    private SyrupRepository syrupRepository;
    private OrderRepository orderRepository;
    private Scanner scanner;

    private App() {
        System.out.println("App Started!\nInitializing...");
        this.dummyData = DummyData.getInstance();
        this.orderRepository = OrderRepository.getInstance();
        initializeRepositories();
        System.out.println("\n\n\n");
    }

    public static synchronized App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    private void initializeRepositories() {
        System.out.println("Loading Flavors...");
        flavorRepository = FlavorRepository.getInstance().initialize(dummyData.getIceCreamFlavors());
        System.out.println("Loading Toppings...");
        toppingRepository = ToppingRepository.getInstance().initialize(dummyData.getIceCreamToppings());
        System.out.println("Loading Syrups...");
        syrupRepository = SyrupRepository.getInstance().initialize(dummyData.getIceCreamSyrups());
        System.out.println("Loading Complete!");
    }

    public void run() {
        System.out.println("Welcome to the Ice Cream Shop!");
        System.out.println("Commands: Place Order / View Order / View All Orders / Quit\n\n");

        while (true) {
            this.scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "place order":
                    Command placeOrderCommand = new PlaceOrderCommand();
                    placeOrderCommand.execute();
                    break;
                case "view order":
                    Command viewOrderCommand = new ViewOrderCommand();
                    viewOrderCommand.execute();
                    break;
                case "view all orders":
                    Command viewAllOrdersCommand = new ViewAllOrdersCommand();
                    viewAllOrdersCommand.execute();
                    break;
                case "quit":
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Please try again.\n");
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

}
