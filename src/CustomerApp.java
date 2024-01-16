import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerApp implements App{

    private static CustomerApp instance;

    private DummyData dummyData;
    private FlavorRepository flavorRepository;
    private ToppingRepository toppingRepository;
    private SyrupRepository syrupRepository;
    private OrderRepository orderRepository;
    private Scanner scanner;
    private String currentUser;
    private Map<String, Order> favoriteOrderMap;

    private CustomerApp() {
        System.out.println("Customer App Started!\nInitializing...\n");
        this.dummyData = DummyData.getInstance();
        this.orderRepository = OrderRepository.getInstance();
        initializeRepositories();
        this.favoriteOrderMap = new HashMap<>();
        System.out.println("\n\n\n");
    }

    public static synchronized CustomerApp getInstance() {
        if (instance == null) {
            instance = new CustomerApp();
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

    public void setCurrentUser(String username) {
        this.currentUser = username;
        if (!favoriteOrderMap.containsKey(username)) {
            favoriteOrderMap.put(username, null);
        }
    }

    @Override
    public void run() {
        System.out.println("Welcome to the Ice Cream Shop!");
        System.out.println("Commands: Place Order / Favorite Order / Pickup Order / Record Feedback / View Order / View All Orders / Quit\n\n");

        while (true) {
            this.scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "place order":
                    Command placeOrderCommand = new PlaceOrderCommand(this.currentUser);
                    placeOrderCommand.execute();
                    break;
                case "favorite order":
                    Command favoriteOrderCommand = new PlaceFavoriteOrderCommand(this.currentUser);
                    favoriteOrderCommand.execute();
                    break;
                case "pickup order":
                    Command pickupOrderCommand = new CompletePickupCommand();
                    pickupOrderCommand.execute();
                    break;
                case "record feedback":
                    Command feedbackCommand = new RecordFeedbackCommand(this.currentUser);
                    feedbackCommand.execute();
                    break;
                case "view order":
                    Command viewOrderCommand = new ViewOrderCommand(this.currentUser);
                    viewOrderCommand.execute();
                    break;
                case "view all orders":
                    Command viewAllOrdersCommand = new ViewAllOrdersOfCustomerCommand(this.currentUser);
                    viewAllOrdersCommand.execute();
                    break;
                case "logout":
                    System.out.println("Logging out...");
                    currentUser = null;
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

    public String getCurrentUser() {
        return currentUser;
    }

    public Map getFavoriteOrderMap(){
        return this.favoriteOrderMap;
    }

}
