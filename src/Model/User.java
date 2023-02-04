package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String username;
    private static ArrayList<User> users = new ArrayList<>();
    private ArrayList<Order> userOrders = new ArrayList<Order>();
    private String type;
    private String password;

    public User(String name, String type, String password) {
        this.username = name;
        this.type = type;
        this.password = password;
        addUser(this);
        JsonManager.writeArrToJson(users,"users.json");
    }

    public static void addUser(User user) {
        for (User currentUser : users) {
            if (user.username.equals(currentUser.username))
                return;
        }
        users.add(user);
    }

    public static User getUserWithUsername(String username) {
        for (User currentUser : users) {
            if (username.equals(currentUser.username))
                return currentUser;
        }
        return null;
    }

    public void addUserOrder(Order order) {
        userOrders.add(order);
    }
    public void removeUserOrder(Order order) {
        userOrders.remove(order);
    }

    public static void createAccount(Scanner myScanner) {
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
                        if (type.equals("customer")) {
                            Logic.setCurrentUser(new Customer(name, type, password));
                        } else if (type.equals("admin")) {
                            Logic.setCurrentUser(new Admin(name, type, password));
                        }
                        System.out.println(ColorManager.GREEN + "You've successfully created an account." + ColorManager.RESET);
                        System.out.println(ColorManager.GREEN + "Logging in as " + name + "..." + ColorManager.RESET);
                        return;
                    }
                }
            }
        }
    }

    public static void login(Scanner myScanner) {
        while (true) {
            System.out.println("Enter your username:");
            String name = myScanner.nextLine();
            if (getUserWithUsername(name) == null)
                System.out.println(ColorManager.RED + "no user found with the associated username, please try again." + ColorManager.RESET);
            else {
                User currentUser = getUserWithUsername(name);
                while (true) {
                    System.out.println("Enter your password:");
                    String password = myScanner.nextLine();
                    if (!password.equals(currentUser.password)) {
                        System.out.println(ColorManager.RED + "Wrong password, please try again." + ColorManager.RESET);
                    } else {
                        System.out.println(ColorManager.GREEN + "You've successfully logged in." + ColorManager.RESET);
                        Logic.setCurrentUser(currentUser);
                        return;
                    }
                }
            }
        }
    }

    public void printUserOptions() {
        System.out.println(ColorManager.YELLOW_BOLD + "\nOptions: (type the given number of the task you want to be done)." + ColorManager.RESET);
//        System.out.println(ColorManager.YELLOW_BOLD + "\t1-View all orders submitted." + ColorManager.RESET);
//        System.out.println(ColorManager.YELLOW_BOLD + "\t2-View the shop's storage." + ColorManager.RESET);
//        System.out.println(ColorManager.YELLOW_BOLD + "\t-enter our coffeeShop or restaurant:" + ColorManager.RESET);
//        System.out.println(ColorManager.YELLOW_BOLD + "\t\t1-Restaurant" + ColorManager.RESET);
//        System.out.println(ColorManager.YELLOW_BOLD + "\t\t2-CoffeeShop" + ColorManager.RESET);
//        System.out.println(ColorManager.YELLOW_BOLD + "\t3-Check your previous orders." + ColorManager.RESET);
    }

    public ArrayList<Order> getUserOrders() {
        return userOrders;
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
            System.out.println(ColorManager.RED + "Your password should contain at least one uppercase, numeric character and special character. please try again." + ColorManager.RESET);
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }
}
