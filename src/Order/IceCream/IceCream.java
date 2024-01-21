package Order.IceCream;

import Order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class IceCream implements OrderItem {

    public enum IceCreamSize {
        CUP,
        BAR,
        CONE,
        TUBE500,
        TUBE1000
    }

    private Flavor flavor;
    private List<Topping> toppings;
    private List<Syrup> syrups;
    private IceCreamSize size;

    public IceCream(Flavor flavor, String size) {
        this.flavor = flavor;
        this.toppings = new ArrayList<>();
        this.syrups = new ArrayList<>();
        this.size = validateSize(size);
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        if (flavor == null) {
            throw new IllegalArgumentException("Order.IceCream.Flavor cannot be null");
        }
        this.flavor = flavor;
    }

    public void addTopping(Topping topping) {
        if (topping == null) {
            throw new IllegalArgumentException("Order.IceCream.Topping cannot be null");
        }
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public void resetToppings() {
        toppings.clear();
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    public void addSyrup(Syrup syrup) {
        if (syrup == null) {
            throw new IllegalArgumentException("Order.IceCream.Syrup cannot be null");
        }
        syrups.add(syrup);
    }

    public void removeSyrup(Syrup syrup) {
        syrups.remove(syrup);
    }

    public void resetSyrups() {
        syrups.clear();
    }

    public List<Syrup> getSyrups() {
        return new ArrayList<>(syrups);
    }

    public void setSize(String size) {
        IceCreamSize iceCreamSize = validateSize(size);
        this.size = iceCreamSize;
    }

    private IceCreamSize validateSize(String size) {
        try {
            return IceCreamSize.valueOf(size.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid ice cream size: " + size);
        }
    }

    @Override
    public String toString() {
        return "Order.IceCream.IceCream{" +
                "flavor=" + flavor +
                ", toppings=" + toppings +
                ", syrups=" + syrups +
                '}';
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Order.IceCream.Flavor: ").append(flavor.getName()).append(", ");
        description.append("Size: ").append(size.name()).append(", ");

        // Toppings
        if (!toppings.isEmpty()) {
            description.append("Toppings: ");
            for (Topping topping : toppings) {
                description.append(topping.getName()).append(", ");
            }
        }

        // Syrups
        if (!syrups.isEmpty()) {
            description.append("Syrups: ");
            for (Syrup syrup : syrups) {
                description.append(syrup.getName()).append(", ");
            }
        }

        return description.toString();
    }

    @Override
    public Double getPrice() {
        double total = flavor.getPrice();

        // Add toppings prices
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }

        // Add syrups prices
        for (Syrup syrup : syrups) {
            total += syrup.getPrice();
        }

        // Apply size multiplier
        switch (size) {
            case CUP:
                total *= 1.0;   // No change for Cup
                break;
            case BAR:
                total *= 2.0;   // Double for Bar
                break;
            case CONE:
                total *= 2.5;   // 2.5 times for Cone
                break;
            case TUBE500:
                total *= 5.0;   // 5 times for Tube500
                break;
            case TUBE1000:
                total *= 8.0;   // 8 times for Tube1000
                break;
        }

        return total;
    }

}
