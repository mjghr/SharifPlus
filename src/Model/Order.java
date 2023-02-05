package Model;

import Controller.UserController;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private UserController user;
    private List<Product> productList;

    public Order(UserController user, List<Product> productList) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.productList = productList;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "id= '" + this.getId() + '\'' + ", user= " + this.getUser().getUserUsername();
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserController getUser() {
        return user;
    }

    public void setUser(UserController user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


}
