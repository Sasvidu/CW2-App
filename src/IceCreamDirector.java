import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IceCreamDirector {

    private Scanner scanner;

    public IceCreamDirector() {
        this.scanner = CustomerApp.getInstance().getScanner();
    }

    public IceCream createIceCream() {
        IceCreamBuilder iceCreamBuilder = new IceCreamBuilder();

        // Get flavor from user
        System.out.print("Enter the name of the flavor: ");
        String flavorName = scanner.nextLine();
        try {
            iceCreamBuilder.setFlavor(flavorName);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Ice Cream Creation Aborted.");
            return null;
        }

        // Get size from user
        System.out.print("Enter the size of the ice cream: ");
        String size = scanner.nextLine();
        try {
            iceCreamBuilder.setSize(size);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Ice Cream Creation Aborted.");
            return null;
        }

        // Get toppings from user
        System.out.print("Enter the list of toppings (separated by comma and space): ");
        String toppingsInput = scanner.nextLine();
        List<String> toppings = Arrays.asList(toppingsInput.split(", "));
        try {
            iceCreamBuilder.addToppings(toppings);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Ice Cream Creation Aborted.");
            return null;
        }

        // Get syrups from user
        System.out.print("Enter the list of syrups (separated by comma and space): ");
        String syrupsInput = scanner.nextLine();
        List<String> syrups = Arrays.asList(syrupsInput.split(", "));
        try {
            iceCreamBuilder.addSyrups(syrups);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Ice Cream Creation Aborted.");
            return null;
        }

        // Build ice cream and return it
        return (IceCream) iceCreamBuilder.build();
    }

    public void closeScanner() {
        scanner.close();
    }
}
