package Model;

import Model.Product.Product;

import java.util.ArrayList;

public abstract class Store {
    private static ArrayList<Order> orders = new ArrayList<>();

    public abstract void getMenu();

    public abstract void addOrder(User user, ArrayList<Product> productList);

    public abstract boolean isOrderValid(String str);

    public abstract String fix(String Str);

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void viewOrders() {
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i+1) + "- " + orders.get(i).toString());
        }
    }

    public static Order getOrderById(String id){
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(id))
                return orders.get(i);
        }
        return null;
    }

    public static void addOrder(Order order){
        orders.add(order);
    }

    public static void removeOrder(Order order){
        orders.remove(order);
        order.getUser().removeUserOrder(order);
    }

}
