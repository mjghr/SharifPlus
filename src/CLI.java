import Controller.*;
import Model.*;
import Model.Product.Product;
import Utils.Ingredient;
import Utils.JsonManager;
import Utils.TextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    private static Scanner myScanner = new Scanner(System.in);
    private static final RestaurantController restaurantController = new RestaurantController();
    private static final CoffeeShopController coffeeShopController = new CoffeeShopController();
    private static final StorageController storageController = new StorageController();
    private static UserController userController;

    public CLI() {
        UserController.readUsersFromJson();

        System.out.println(TextFormatter.GREEN_BRIGHT + "\nWelcome to SharifPlus!" + TextFormatter.RESET);
        System.out.println("\n" + TextFormatter.YELLOW_BRIGHT + "-You can always go back to the previous page by typing \"back\"."
                + TextFormatter.RESET);
        System.out.println(TextFormatter.YELLOW_BRIGHT + "-You can always logout of your account by typing" +
                " \"logout\" after you login." + TextFormatter.RESET + "\n");

        while (true) {
            System.out.println("Already have an account? (y/n)");
            String answer = myScanner.nextLine().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                userController = UserController.login(myScanner);
                if (userController == null) {
                    continue;
                }
            } else if (answer.equals("n") || answer.equals("no")) {
                userController = UserController.createAccount(myScanner);
                if (userController == null) {
                    continue;
                }
            }

            if (answer.equals("y") || answer.equals("yes") || answer.equals("n") || answer.equals("no")) {
                if (userController.getUserType().equals("customer")) {
                    a1:
                    while (true) {
                        userController.printUserOptions();
                        answer = myScanner.nextLine();
                        if (answer.equals("back")) {
                            System.out.println("You're logging out of your account...");
                            break a1;
                        } else if (answer.equals("logout")) {
                            System.out.println("You're logging out of your account...");
                            break a1;
                        } else if (answer.equals("1")) {
                            restaurantController.checkAvailability(storageController.getStorageItems());
                            restaurantController.getMenu();
                            b:
                            while (true) {
                                HashMap<Ingredient, Integer> tempMap = new HashMap<>(storageController.getStorageItems());
                                System.out.println(TextFormatter.BLUE_BRIGHT + "\nEnter the name of the product you want to buy." + TextFormatter.RESET);
                                answer = myScanner.nextLine().toLowerCase();
                                if (answer.equals("back"))
                                    break b;
                                else if (answer.equals("logout")) {
                                    break a1;
                                }
                                ArrayList<String> orders = new ArrayList<>(Arrays.asList(restaurantController.fix(answer).split(" ")));
                                ArrayList<Product> productList = new ArrayList<>();
                                boolean flag = true;
                                n:
                                for (int i = 0; i < orders.size(); i++) {
                                    if (!restaurantController.isOrderValid(orders.get(i))) {
                                        System.out.println(TextFormatter.RED + "invalid product names. please try again." + TextFormatter.RESET);
                                        flag = false;
                                        storageController.setItems(tempMap);
                                        break n;
                                    } else {
                                        Product product = restaurantController.getRestaurantProducts().get(orders.get(i));
                                        productList.add(product);
                                        Ingredient[] ingredients = product.getIngredient();
                                        for (int j = 0; j < ingredients.length; j++) {
                                            if (storageController.getStorageItems().get(ingredients[j]) <= 0) {
                                                System.out.println(TextFormatter.RED + "I'm afraid we don't have enough items for this product. try ordering again." + TextFormatter.RESET);
                                                flag = false;
                                                storageController.setItems(tempMap);
                                                break n;
                                            }
                                        }

                                        for (int j = 0; j < ingredients.length; j++) {
                                            storageController.decreaseIngredient(ingredients[j], 1);
                                        }
                                    }
                                }

                                storageController.setItems(tempMap);

                                if (flag) {
                                    for (int i = 0; i < orders.size(); i++) {
                                        Product product = restaurantController.getRestaurantProducts().get(orders.get(i));
                                        Ingredient[] ingredients = product.getIngredient();
                                        for (int j = 0; j < ingredients.length; j++) {
                                            storageController.decreaseIngredient(ingredients[j], 1);
                                        }
                                    }
                                    restaurantController.addOrder(userController, productList);
                                    c:
                                    while (true) {
                                        System.out.println("would you like to order anything else? (y/n)");
                                        answer = myScanner.nextLine();
                                        if (answer.equals("back"))
                                            break b;
                                        else if (answer.equals("logout")) {
                                            break a1;
                                        }
                                        if (answer.equals("n") || answer.equals("no")) {
                                            System.out.println(TextFormatter.GREEN_BRIGHT + "\nFinished ordering." + TextFormatter.RESET);
                                            System.out.println(TextFormatter.GREEN_BRIGHT + "going back to the first page..." + TextFormatter.RESET);
                                            break b;
                                        } else if (answer.equals("y") || answer.equals("yes")) {
                                            break c;
                                        }
                                    }
                                }
                            }
                        } else if (answer.equals("2")) {
                            coffeeShopController.checkAvailability(storageController.getStorageItems());
                            coffeeShopController.getMenu();
                            b:
                            while (true) {
                                HashMap<Ingredient, Integer> tempMap = new HashMap<>(storageController.getStorageItems());
                                System.out.println(TextFormatter.BLUE_BRIGHT + "\nEnter the name of the product you want to buy." + TextFormatter.RESET);
                                answer = myScanner.nextLine().toLowerCase();
                                if (answer.equals("back"))
                                    break b;
                                else if (answer.equals("logout")) {
                                    break a1;
                                }
                                ArrayList<String> orders = new ArrayList<>(Arrays.asList(coffeeShopController.fix(answer).split(" ")));
                                ArrayList<Product> productList = new ArrayList<>();
                                boolean flag = true;
                                n:
                                for (int i = 0; i < orders.size(); i++) {
                                    if (!coffeeShopController.isOrderValid(orders.get(i))) {
                                        System.out.println(TextFormatter.RED + "invalid product names. please try again." + TextFormatter.RESET);
                                        flag = false;
                                        storageController.setItems(tempMap);
                                        break n;
                                    } else {
                                        Product product = coffeeShopController.getCoffeeShopProducts().get(orders.get(i));
                                        productList.add(product);
                                        Ingredient[] ingredients = product.getIngredient();
                                        for (int j = 0; j < ingredients.length; j++) {
                                            if (storageController.getStorageItems().get(ingredients[j]) <= 0) {
                                                System.out.println(TextFormatter.RED + "I'm afraid we don't have enough items for this product. try ordering again." + TextFormatter.RESET);
                                                flag = false;
                                                storageController.setItems(tempMap);
                                                break n;
                                            }
                                        }

                                        for (int j = 0; j < ingredients.length; j++) {
                                            storageController.decreaseIngredient(ingredients[j], 1);
                                        }
                                    }
                                }

                                storageController.setItems(tempMap);

                                if (flag) {
                                    for (int i = 0; i < orders.size(); i++) {
                                        Product product = coffeeShopController.getCoffeeShopProducts().get(orders.get(i));
                                        Ingredient[] ingredients = product.getIngredient();
                                        for (int j = 0; j < ingredients.length; j++) {
                                            storageController.decreaseIngredient(ingredients[j], 1);
                                        }
                                    }

                                    coffeeShopController.addOrder(userController, productList);
                                    c:
                                    while (true) {
                                        System.out.println("would you like to order anything else? (y/n)");
                                        answer = myScanner.nextLine();
                                        if (answer.equals("back"))
                                            break b;
                                        else if (answer.equals("logout")) {
                                            break a1;
                                        }
                                        if (answer.equals("n") || answer.equals("no")) {
                                            System.out.println(TextFormatter.GREEN_BRIGHT + "\nFinished ordering." + TextFormatter.RESET);
                                            System.out.println(TextFormatter.GREEN_BRIGHT + "going back to the first page..." + TextFormatter.RESET);
                                            break b;
                                        } else if (answer.equals("y") || answer.equals("yes")) {
                                            break c;
                                        }
                                    }
                                }
                            }
                        } else if (answer.equals("3")) {
                            while (true) {
                                System.out.println("view coffeeShop's menu or restaurant's menu? (coffeeShop/restaurant)");
                                answer = myScanner.nextLine().toLowerCase();
                                if (answer.equals("back")) {
                                    break;
                                } else if (answer.equals("logout")) {
                                    break a1;
                                } else if (answer.equals("restaurant")) {
                                    restaurantController.getMenu();
                                    while (true) {
                                        System.out.println("Enter a product name.");
                                        answer = myScanner.nextLine().toLowerCase();
                                        if (answer.equals("back")) {
                                            break;
                                        } else if (answer.equals("logout")) {
                                            break a1;
                                        }
                                        if (restaurantController.isOrderValid(answer)) {
                                            Product product = restaurantController.getRestaurantProducts().get(restaurantController.fix(answer));
                                            product.printIngredient();
                                        }
                                    }
                                } else if (answer.equals("coffeeshop")) {
                                    coffeeShopController.getMenu();
                                    while (true) {
                                        System.out.println("Enter a product name.");
                                        answer = myScanner.nextLine().toLowerCase();
                                        if (answer.equals("back")) {
                                            break;
                                        } else if (answer.equals("logout")) {
                                            break a1;
                                        } else if (coffeeShopController.isOrderValid(coffeeShopController.fix(answer))) {
                                            Product product = coffeeShopController.getCoffeeShopProducts().get(coffeeShopController.fix(answer));
                                            product.printIngredient();
                                        }
                                    }
                                }
                            }
                        } else if (answer.equals("4")) {
                            System.out.println(TextFormatter.BLUE_BRIGHT + "\nHere's a list of your previous orders:" + TextFormatter.RESET);
                            userController.viewUserOrders();
                        } else {
                            System.out.println(TextFormatter.RED + "Invalid input, please try again." + TextFormatter.RESET);
                        }
                    }
                } else if (userController.getUserType().equals("admin")) {
                    cc:
                    while (true) {
                        userController.printUserOptions();
                        answer = myScanner.nextLine().toLowerCase();
                        if (answer.equals("back")) {
                            System.out.println("You're logging out of your account...");
                            break cc;
                        } else if (answer.equals("logout")) {
                            System.out.println("You're logging out of your account...");
                            break cc;
                        } else if (answer.equals("1")) {
                            if(OrderController.viewOrders()) {
                                k:
                                while (true) {
                                    System.out.println("Would you like to cancel any orders? (y/n)");
                                    answer = myScanner.nextLine().toLowerCase();
                                    if (answer.equals("back")) {
                                        break k;
                                    } else if (answer.equals("logout")) {
                                        break cc;
                                    } else if (answer.equals("y") || answer.equals("yes")) {
                                        l:
                                        while (true) {
                                            System.out.println("Enter the id of the order you want to be cancelled.");
                                            answer = myScanner.nextLine().toLowerCase();
                                            if (answer.equals("logout")) {
                                                break cc;
                                            } else if (answer.equals("back")) {
                                                break l;
                                            }
                                            Order order = OrderController.getOrderById(answer);
                                            if (order == null) {
                                                System.out.println(TextFormatter.RED + "No order found with the id given. please try again." + TextFormatter.RESET);
                                            } else {
                                                OrderController.removeOrder(order);
                                                System.out.println(TextFormatter.GREEN_BRIGHT + "\nOrder successfully deleted." + TextFormatter.RESET);
                                                break l;
                                            }
                                        }
                                    } else if (answer.equals("n") || answer.equals("no")) {
                                        System.out.println(TextFormatter.GREEN_BRIGHT + "\nGoing back to the first page..." + TextFormatter.GREEN);
                                        break k;
                                    }
                                }
                            }
                        } else if (answer.equals("2")) {
                            storageController.printStorage();
                            j:
                            while (true) {
                                System.out.println("1- increase the amount of a certain ingredient");
                                System.out.println("2- increase or decrease the amount of all ingredients.");
                                answer = myScanner.nextLine().toLowerCase();
                                if (answer.equals("back")) {
                                    break j;
                                } else if (answer.equals("logout")) {
                                    break cc;
                                } else if (answer.equals("1")) {
                                    o:
                                    while (true) {
                                        System.out.println("What do you want to add? enter a ingredient.");
                                        answer = myScanner.nextLine().toLowerCase();
                                        if (answer.equals("back")) {
                                            break;
                                        } else if (answer.equals("logout")) {
                                            break cc;
                                        }
                                        Ingredient ing = storageController.getIngredientByName(answer);
                                        if (ing == null) {
                                            System.out.println(TextFormatter.RED + "I'm afraid we don't have such ingredient in our storage, try again." + TextFormatter.RESET);
                                        } else {
                                            s:
                                            while (true) {
                                                System.out.println("How much you want " + ing.name() + " to increase. (Enter a number)");
                                                answer = myScanner.nextLine().toLowerCase();
                                                if (answer.equals("back")) {
                                                    break;
                                                } else if (answer.equals("logout")) {
                                                    break cc;
                                                }
                                                try {
                                                    int amount = Integer.parseInt(answer);
                                                    if (storageController.increaseIngredient(ing, amount)) {
                                                        System.out.println(TextFormatter.GREEN_BRIGHT + "\nThe amount of " + ing.name() + " increased by " + amount + "." + TextFormatter.RESET);
                                                        JsonManager.writeLogToText("Admin \"" + userController.getUserUsername() + "\" increased Ingredient \"" + ing.name() + "\" by " + amount + ".");
                                                        break o;
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println(TextFormatter.RED + "Your input should only contain numbers." + TextFormatter.RESET);
                                                }
                                            }
                                        }
                                    }
                                } else if (answer.equals("2")) {
                                    hh:
                                    while (true) {
                                        System.out.println("decrease or increase? (increase/decrease)");
                                        answer = myScanner.nextLine().toLowerCase();
                                        if (answer.equals("back")) {
                                            break hh;
                                        } else if (answer.equals("logout")) {
                                            break cc;
                                        } else if (answer.equals("decrease")) {
                                            while (true) {
                                                System.out.println("Enter the amount.");
                                                answer = myScanner.nextLine().toLowerCase();
                                                if (answer.equals("back")) {
                                                    break;
                                                } else if (answer.equals("logout")) {
                                                    break cc;
                                                } else {
                                                    try {
                                                        int amount = Integer.parseInt(answer);
                                                        storageController.decreaseAllIngredients(amount);
                                                        System.out.println(TextFormatter.GREEN_BRIGHT + "The amount of all decreased by " + amount + TextFormatter.RESET);
                                                        JsonManager.writeLogToText("Admin \"" + userController.getUserUsername() + "\" decreased all Ingredients by " + amount);
                                                        break hh;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println(TextFormatter.RED + "Invalid input, please enter a number." + TextFormatter.RESET);
                                                    }
                                                }
                                            }
                                        } else if (answer.equals("increase")) {
                                            while (true) {
                                                System.out.println("Enter the amount.");
                                                answer = myScanner.nextLine().toLowerCase();
                                                if (answer.equals("back")) {
                                                    break;
                                                } else if (answer.equals("logout")) {
                                                    break cc;
                                                } else {
                                                    try {
                                                        int amount = Integer.parseInt(answer);
                                                        if (storageController.increaseAllIngredients(amount)) {
                                                            System.out.println(TextFormatter.GREEN_BRIGHT + "The amount of all increased by " + amount + TextFormatter.RESET);
                                                            JsonManager.writeLogToText("Admin \"" + userController.getUserUsername() + "\" increased all Ingredients by " + amount);
                                                            break hh;
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        System.out.println(TextFormatter.RED + "Invalid input, please enter a number." + TextFormatter.RESET);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println(TextFormatter.RED + "Invalid input, please try again." + TextFormatter.RESET);
                                }
                            }
                        } else {
                            System.out.println(TextFormatter.RED + "Invalid input, please try again." + TextFormatter.RESET);
                        }
                    }
                }
            }
        }
    }
}
