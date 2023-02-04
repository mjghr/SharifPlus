package Model;

import Model.Product.Product;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private User user;
    private List<Product> productList;

    public Order(User user, List<Product> productList) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "id= '" + id + '\'' + ", user= " + user.getUsername();
    }

    public static void submitOrder(Order order) {

    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
