public class Main {

    public static final String dummyOrigin = "origin";

    public static void main(String[] args) {
        System.out.println("\n\nApp Started!\n");
        initializeLoginData();
        while (true){
            Command loginCommand = new LoginCommand();
            loginCommand.execute();
        }
    }

    private static void initializeLoginData(){
        LoginDummyData data = LoginDummyData.getInstance();
        CustomerRepository customers = CustomerRepository.getInstance();
        DelivererRepository deliverers = DelivererRepository.getInstance();
        AdminRepository admins = AdminRepository.getInstance();

        for (Object[] record: data.getCustomerList()) {
            customers.addCustomer(record[0].toString(), record[1].toString());
        }
        for (Object[] record: data.getDelivererList()) {
            deliverers.addDeliverer(record[0].toString(), record[1].toString());
        }
        for (Object[] record: data.getAdminList()) {
            admins.addAdmin(record[0].toString(), record[1].toString());
        }
    }

}
