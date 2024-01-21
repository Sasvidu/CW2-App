package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Order.IceCream.*;
import Payments.*;
import APIs.*;

public class ViewAllOrdersCommand implements Command {

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        orderRepository.printAllOrders();
    }

}
