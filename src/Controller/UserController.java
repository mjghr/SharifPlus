package Controller;

import Model.*;
import Model.Product.Product;
import Utils.JsonManager;
import Utils.TextFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    private User model;
    private static ArrayList<User> users = new ArrayList<>();

    public UserController(User model, boolean dontWrite) {
        this.model = model;
        if (!dontWrite) {
//            writeToFile();
        }
    }

    public static void writeToFile() {
        JsonManager.writeArrToJson(users, "src\\resources\\users.json");
    }


    public static void readUsersFromJson() {
        if (JsonManager.readUsersFromJson("src\\resources\\users.json") != null) {
            users = JsonManager.readUsersFromJson("src\\resources\\users.json");
        }
    }

    public static UserController createAccount(Scanner myScanner) {
        while (true) {
            System.out.println("Enter your username:");
            String name = myScanner.nextLine();
            if (name.equalsIgnoreCase("back")) {
                return null;
            }

            if (getUserWithUsername(name) != null) {
                System.out.println(TextFormatter.RED + "Username already in use, please try again" + TextFormatter.RESET);
                return createAccount(myScanner);
            }

            while (true) {
                System.out.println("Enter your password:");
                String password = myScanner.nextLine();

                if (password.equalsIgnoreCase("back")) {
                    return createAccount(myScanner);
                }

                if (checkPassword(password)) {
                    a:
                    while (true) {
                        System.out.println("Are you an customer or an admin? (admin/customer)");
                        String type = myScanner.nextLine().toLowerCase();
                        if (type.equalsIgnoreCase("back")) {
                            break a;
                        }
                        if (type.equals("customer") || type.equals("admin")) {
                            System.out.println(TextFormatter.GREEN_BRIGHT + "\nYou've successfully created an account." + TextFormatter.RESET);
                            System.out.println(TextFormatter.GREEN_BRIGHT + "Logging in as " + name + "..." + TextFormatter.RESET);
                            users.add(new User(name, type, password));
                            JsonManager.writeLogToText("User created: type= \"" + type + "\" username= \"" + name + "\" password= \"" + password + "\".");
                            return new UserController(new User(name, type, password), false);
                        }
                    }
                }
            }
        }
    }

    public static UserController login(Scanner myScanner) {
        while (true) {
            System.out.println("Enter your username:");
            String name = myScanner.nextLine();
            if (name.equalsIgnoreCase("back")) {
                return null;
            }
            if (getUserWithUsername(name) == null)
                System.out.println(TextFormatter.RED + "no user found with the associated username, please try again." + TextFormatter.RESET);
            else {
                User currentUser = getUserWithUsername(name);
                while (true) {
                    System.out.println("Enter your password:");
                    String password = myScanner.nextLine();
                    if (password.equalsIgnoreCase("back")) {
                        break;
                    }
                    if (!password.equals(currentUser.getPassword())) {
                        System.out.println(TextFormatter.RED + "Wrong password, please try again." + TextFormatter.RESET);
                    } else {
                        JsonManager.writeLogToText("User \"" + name + "\" has logged in.");
                        System.out.println(TextFormatter.GREEN_BRIGHT + "\nYou've successfully logged in." + TextFormatter.RESET);
                        users.add(currentUser);
                        return new UserController(currentUser, true);
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

    public static User getUserWithUsername(String username) {
        for (User currentUser : users) {
            if (username.equals(currentUser.getUsername()))
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
        System.out.println(TextFormatter.YELLOW_BRIGHT + "\nOptions: (type the given number of the task you want to be done)." + TextFormatter.RESET);
        if (this.getUserType().equals("admin")) {
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t1-View all orders submitted." + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t2-View the shop's storage." + TextFormatter.RESET);
        } else {
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t-enter our coffeeShop or restaurant to order:" + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t\t1-Restaurant" + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t\t2-CoffeeShop" + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t3-View our menu to check the products' ingredients." + TextFormatter.RESET);
            System.out.println(TextFormatter.YELLOW_BRIGHT + "\t4-Check your previous orders." + TextFormatter.RESET);
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

    public void viewUserOrders() {
        ArrayList<Order> orders = model.getUserOrders();
        if (orders.size() == 0) {
            System.out.println(TextFormatter.RED + "No orders submitted so far." + TextFormatter.RESET);
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            ArrayList<Product> products = orders.get(i).getProductList();
            System.out.print(i + 1 + "- ");
            for (int j = 0; j < orders.get(i).getProductList().size(); j++) {
                if (j == orders.get(i).getProductList().size() - 1) {
                    System.out.print(products.get(j).getName());
                } else {
                    System.out.print(products.get(j).getName() + ", ");
                }
            }
            System.out.println();
        }
    }

    public String getUserType() {
        return this.model.getType();
    }

    public String getUserUsername() {
        return this.model.getUsername();
    }

    public User getModel() {
        return this.model;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        UserController.users = users;
    }

}
