public class GiftWrapperHandler extends BaseWrapperHandler{

    @Override
    public void handleRequest(Order order) {
        System.out.print("Do you want gift wrapping? (Yes/No): ");
        String response = CustomerApp.getInstance().getScanner().nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            order.addWrapper(new GiftWrapper(order.getWrapper()));
        }

        handleNext(order);
    }

}
