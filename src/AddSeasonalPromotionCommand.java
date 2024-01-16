import java.util.Scanner;

public class AddSeasonalPromotionCommand implements Command {

    @Override
    public void execute() {
        Scanner scanner = AdminApp.getInstance().getScanner();

        System.out.print("Enter discount rate for the new seasonal promotion (e.g., 0.1 for 10%): ");
        double discountRate = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter a description for the new seasonal promotion: ");
        String description = scanner.nextLine().trim();

        PromotionStrategy seasonalPromotion = new SeasonalPromotion(discountRate, description);
        PromotionRepository.getInstance().addPromotionStrategy(seasonalPromotion);

        System.out.println("New seasonal promotion added successfully!\n\n");
    }

}
