package Repositories;

import Order.IceCream.*;
import java.util.HashMap;
import java.util.Map;

public class ToppingRepository {

    private static ToppingRepository instance;
    private Map<String, Topping> toppings;

    private ToppingRepository() {
        this.toppings = new HashMap<>();
    }

    public static ToppingRepository getInstance() {
        if (instance == null) {
            instance = new ToppingRepository();
        }
        return instance;
    }

    public ToppingRepository initialize(String[] toppingData) {
        if (toppingData.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid topping data array. It should have pairs of name and price.");
        }

        for (int i = 0; i < toppingData.length; i += 2) {
            String name = toppingData[i];
            double price = Double.parseDouble(toppingData[i + 1]);
            Topping topping = new Topping(name, price);
            toppings.put(name, topping);
        }

        return this;
    }

    public Topping getToppingByName(String name) {
        return toppings.get(name);
    }

    public void addTopping(String name, double price) {
        Topping topping = new Topping(name, price);
        toppings.put(name, topping);
    }

    public void removeTopping(String name) {
        Topping removedTopping = toppings.remove(name);
        if (removedTopping != null) {
            System.out.println("Order.IceCream.Topping removed: " + removedTopping);
        } else {
            System.out.println("Order.IceCream.Topping not found: " + name);
        }
    }

    //Debug:
    public Map getToppings(){return this.toppings;}

}
