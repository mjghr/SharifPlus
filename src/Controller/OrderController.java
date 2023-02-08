package Controller;

import Model.Order;
import Model.Product.Product;
import Utils.JsonManager;
import Utils.TextFormatter;

import java.util.ArrayList;

public class OrderController {
    private static ArrayList<Order> orders = new ArrayList<>();

    private static void addToOrders(Order order){
        JsonManager.writeArrToJson(orders,"orders.json");
    }
    public static void readOrdersFromJson() {
        orders = JsonManager.readOrderFromJson("orders.json");
    }

    public static void removeOrder(Order order) {
        orders.remove(order);
        order.getUser().removeUserOrder(order);
    }

    public static void viewOrders() {
        if (orders.size()==0){
            System.out.println(TextFormatter.RED + "No orders submitted so far." + TextFormatter.RESET);
            return;
        }
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
        JsonManager.writeArrToJson(orders, "orders.json");
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }
}
