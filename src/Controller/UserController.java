package Controller;

import Model.*;
import Utils.JsonManager;
import Utils.TextFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    private User model;
    private static ArrayList<User> users = new ArrayList<>();

    public UserController(User model) {
        this.model = model;
        JsonManager.writeArrToJson(users,"users.json");
    }

    public static UserController createAccount(Scanner myScanner) {
        System.out.println("Enter your username:");
        String name = myScanner.nextLine();
        while (true) {
            System.out.println("Enter your password:");
            String password = myScanner.nextLine();
            if (checkPassword(password)) {
                while (true) {
                    System.out.println("Are you an customer or an admin? (admin/customer)");
                    String type = myScanner.nextLine().toLowerCase();
                    if (type.equals("customer") || type.equals("admin")) {
                        System.out.println(TextFormatter.GREEN + "You've successfully created an account." + TextFormatter.RESET);
                        System.out.println(TextFormatter.GREEN + "Logging in as " + name + "..." + TextFormatter.RESET);
                        users.add(new User(name,type,password));
                        return new UserController(new User(name,type,password));
                    }
                }
            }
        }
    }

    public UserController login(Scanner myScanner) {
        while (true) {
            System.out.println("Enter your username:");
            String name = myScanner.nextLine();
            if (getUserWithUsername(name) == null)
                System.out.println(TextFormatter.RED + "no user found with the associated username, please try again." + TextFormatter.RESET);
            else {
                User currentUser = getUserWithUsername(name);
                while (true) {
                    System.out.println("Enter your password:");
                    String password = myScanner.nextLine();
                    if (!password.equals(currentUser.getPassword())) {
                        System.out.println(TextFormatter.RED + "Wrong password, please try again." + TextFormatter.RESET);
                    } else {
                        System.out.println(TextFormatter.GREEN + "You've successfully logged in." + TextFormatter.RESET);
                        users.add(currentUser);
                        return new UserController(currentUser);
                    }
                }
            }
        }
    }

    public void addUser(User user) {
        for (User currentUser : users) {
            if (user.getUsername().equals(currentUser.getUsername()))
                return;
        }
        users.add(user);
    }

    public User getUserWithUsername(String username) {
        for (User currentUser : users) {
            if (username.equals(model.getUsername()))
                return currentUser;
        }
        return null;
    }

    public void addUserOrder(Order order) {
        model.getUserOrders().add(order);
    }

    public void removeUserOrder(Order order) {
        model.getUserOrders().remove(order);
    }


    public void printUserOptions() {
        System.out.println(TextFormatter.YELLOW_BOLD + "\nOptions: (type the given number of the task you want to be done)." + TextFormatter.RESET);
        if (this.getUserType().equals("admin")) {
            System.out.println(TextFormatter.YELLOW_BOLD + "\t1-View all orders submitted." + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BOLD + "\t\tyou can remove an order." + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BOLD + "\t2-View the shop's storage." + TextFormatter.RESET);
        } else {
            System.out.println(TextFormatter.YELLOW_BOLD + "\t-enter our coffeeShop or restaurant:" + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BOLD + "\t\t1-Restaurant" + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BOLD + "\t\t2-CoffeeShop" + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BOLD + "\t3-Check your previous orders." + TextFormatter.RESET);
        }
    }

    public static boolean checkPassword(String pass) {
        boolean uppercase = false;
        boolean containsChar = false;
        boolean containsNumber = false;
        String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^_`{|}";

        for (int i = 0; i < pass.length(); i++) {
            char currentChar = pass.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                uppercase = true;
            }
            if (Character.isDigit(currentChar)) {
                containsNumber = true;
            }
            if (specialCharacters.contains(Character.toString(currentChar))) {
                containsChar = true;
            }

        }
        if (uppercase && containsChar && containsNumber) {

            return true;
        } else {
            System.out.println(TextFormatter.RED + "Your password should contain at least one uppercase, numeric character and special character. please try again." + TextFormatter.RESET);
            return false;
        }
    }

    public String getUserType(){
        return this.model.getType();
    }

    public String getUserUsername(){
        return this.model.getUsername();
    }

    public User getModel(){
        return this.model;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        UserController.users = users;
    }
}
