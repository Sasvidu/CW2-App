package Repositories;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {

    private static CustomerRepository instance;
    private Map<String, String> customers;

    private CustomerRepository() {
        customers = new HashMap<>();
    }

    public static synchronized CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void addCustomer(String username, String password) {
        customers.put(username, password);
    }

    public Map<String, String> getCustomers() {
        return customers;
    }

}