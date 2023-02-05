package Controller;

import Model.Order;
import Model.Product.Product;

import java.util.ArrayList;

public class OrderController {
    private static ArrayList<Order> orders = new ArrayList<>();

    private Order model;

    public OrderController(Order model) {
        this.model = model;
    }

    public static void submitOrder(Order order) {

    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static void removeOrder(Order order) {
        orders.remove(order);
        order.getUser().removeUserOrder(order);
    }

    public static void viewOrders() {
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + "- " + orders.get(i).toString());
        }
    }

    public static Order getOrderById(String id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(id))
                return orders.get(i);
        }
        return null;
    }

    public static void addOrder(UserController user, ArrayList<Product> productList) {
        Order order = new Order(user, productList);
        user.addUserOrder(order);
        orders.add(order);
    }

}
