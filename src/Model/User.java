package Model;

import Controller.UserController;

import java.util.ArrayList;

public class User {

    private String username;
    private ArrayList<Order> userOrders = new ArrayList<Order>();
    private String type;
    private String password;

    public User(String name, String type, String password) {
        this.username = name;
        this.type = type;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(ArrayList<Order> userOrders) {
        this.userOrders = userOrders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
