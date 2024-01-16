import java.util.List;
import java.util.Scanner;

public class SelectPromotionCommand implements Command {

    private Order order;

    public SelectPromotionCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        Scanner scanner = CustomerApp.getInstance().getScanner();

        // Display available promotions
        System.out.println("Available Promotions:");
        List<PromotionStrategy> promotionStrategies = PromotionRepository.getInstance().getAllPromotionStrategies();
        for (int i = 0; i < promotionStrategies.size(); i++) {
            PromotionStrategy strategy = promotionStrategies.get(i);
            System.out.println((i + 1) + ". " + strategy.getDescription());
        }

        // Prompt user to select a promotion
        int selectedPromotionIndex;
        do {
            System.out.print("Select a promotion by entering its number (1-" + promotionStrategies.size() + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            selectedPromotionIndex = scanner.nextInt();
        } while (selectedPromotionIndex < 1 || selectedPromotionIndex > promotionStrategies.size());

        // Apply the selected promotion to the order
        PromotionStrategy selectedPromotion = promotionStrategies.get(selectedPromotionIndex - 1);
        PromotionContext promotionContext = new PromotionContext();
        promotionContext.setPromotionStrategy(selectedPromotion);
        promotionContext.applyPromotion(order);

        System.out.println("Promotion applied: " + selectedPromotion.getDescription());
    }

}
