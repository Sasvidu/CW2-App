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
        Map<String, String> admins = AdminRepository.getInstance().getAdmins();
        Map<String, String> deliverers = DelivererRepository.getInstance().getDeliverers();

        if (customers.containsKey(username) && customers.get(username).equals(password)) {
            System.out.println("Customer Login successful, " + username + "!\n\n");
            CustomerApp app = CustomerApp.getInstance();
            app.setCurrentUser(username);
            app.run();
        } else if(admins.containsKey(username) && admins.get(username).equals(password)){
            System.out.println("Admin Login successful, " + username + "!\n\n");
            AdminApp app = AdminApp.getInstance();
            app.run();
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }

}
