public class LoginDummyData {

    private static LoginDummyData instance;

    private Object[][] customerList = {
            {"customer1", "customerpassword1"},
            {"customer2", "customerpassword2"},
            {"customer3", "customerpassword3"},
    };

    private Object[][] delivererList = {
            {"deliverer1", "delivererpassword1"},
            {"deliverer2", "delivererpassword2"},
            {"deliverer3", "delivererpassword3"},
    };

    private Object[][] adminList = {
            {"admin1", "adminpassword1"},
            {"admin2", "adminpassword2"},
    };

    private LoginDummyData() {}

    public static synchronized LoginDummyData getInstance() {
        if (instance == null) {
            instance = new LoginDummyData();
        }
        return instance;
    }

    public Object[][] getCustomerList() {
        return customerList;
    }

    public Object[][] getDelivererList() {
        return delivererList;
    }

    public Object[][] getAdminList() {
        return adminList;
    }

}
