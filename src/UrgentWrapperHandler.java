public class UrgentWrapperHandler extends BaseWrapperHandler {

    @Override
    public void handleRequest(Order order) {
        System.out.print("Do you want urgent delivery? (Yes/No): ");
        String response = App.getInstance().getScanner().nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            order.addWrapper(new UrgentWrapper(order.getWrapper()));
        }

        handleNext(order);
    }

}
