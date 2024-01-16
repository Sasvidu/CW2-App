import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginCommand implements Command {

    private Scanner scanner;

    @Override
    public void execute() {
        System.out.println("Login...");
        this.scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        Map<String, String> customers = CustomerRepository.getInstance().getCustomers();
        if (customers.containsKey(username) && customers.get(username).equals(password)) {
            System.out.println("Customer Login successful, " + username + "!\n\n");
            CustomerApp app = CustomerApp.getInstance();
            app.setCurrentUser(username);
            app.run();
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }
}
