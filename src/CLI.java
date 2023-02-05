import Controller.*;
import Model.CoffeeShop;
import Model.Restaurant;
import Model.*;
import Model.Product.Product;
import Utils.Ingredient;
import Utils.TextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLI {
    private static Scanner myScanner = new Scanner(System.in);
    private static final RestaurantController restaurantController = new RestaurantController(new Restaurant());
    private static final CoffeeShopController coffeeShopController = new CoffeeShopController(new CoffeeShop());
    private static final StorageController storageController = new StorageController(new Storage());
    private static UserController userController;

    public CLI() {
        System.out.println(TextFormatter.GREEN_BOLD + "Welcome to SharifPlus!" + TextFormatter.RESET);
        System.out.println("\n" + TextFormatter.YELLOW_BOLD + "-You can always go back to the previous page by typing \"back\"."
                + TextFormatter.RESET);
        System.out.println(TextFormatter.YELLOW_BOLD + "-You can always logout of your account by typing" +
                " \"logout\" after you login." + TextFormatter.RESET + "\n");

        while (true) {
            System.out.println("Already have an account? (y/n)");
            String answer = myScanner.nextLine().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                userController = userController.login(myScanner);
            } else if (answer.equals("n") || answer.equals("no")) {
                userController = UserController.createAccount(myScanner);
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
                            restaurantController.getMenu();
                            System.out.println(TextFormatter.BLUE + "Enter the name of the product you want to buy." + TextFormatter.RESET);
                            while (true) {
                                break;
                            }
                        } else if (answer.equals("2")) {

                            coffeeShopController.getMenu();
                            b:
                            while (true) {
                                System.out.println(TextFormatter.BLUE + "Enter the name of the product you want to buy." + TextFormatter.RESET);
                                answer = myScanner.nextLine().toLowerCase();
                                if (answer.equals("back"))
                                    break b;
                                else if (answer.equals("logout")) {
                                    break a1;
                                }
                                ArrayList<String> orders = new ArrayList<>(Arrays.asList(coffeeShopController.fix(answer).split(" ")));
                                ArrayList<Product> productList = new ArrayList<>();
                                boolean gg = true;
                                n:
                                for (int i = 0; i < orders.size(); i++) {
                                    if (!coffeeShopController.isOrderValid(orders.get(i))) {
                                        System.out.println(TextFormatter.RED + "invalid product names. please try again." + TextFormatter.RESET);
                                        gg = false;
                                        break n;
                                    } else {
                                        Product product = coffeeShopController.getCoffeeShopProducts().get(orders.get(i));
                                        productList.add(product);
                                        Ingredient[] ingredients = product.getIngredient();
                                        for (int j = 0; j < ingredients.length; j++) {
                                            if (storageController.getStorageItems().get(ingredients[j]) == 0) {
                                                System.out.println(TextFormatter.RED + "I'm afraid we don't have enough items for this product. try ordering again." + TextFormatter.RESET);
                                                gg = false;
                                                break n;
                                            }
                                            storageController.decreaseIngredient(ingredients[j],1);
                                        }

                                    }
                                }
                                if (gg) {
                                    OrderController.addOrder(userController, productList);
                                    c:
                                    while (true) {
                                        System.out.println("would you like to order anything else? (y/n)");
                                        answer = myScanner.nextLine();
                                        if (answer.equals("back"))
                                            break c;
                                        else if (answer.equals("logout")) {
                                            break a1;
                                        }
                                        if (answer.equals("n") || answer.equals("no")) {
                                            System.out.println(TextFormatter.GREEN_BOLD_BRIGHT + "Finished ordering." + TextFormatter.RESET);
                                            System.out.println(TextFormatter.GREEN_BOLD_BRIGHT + "going back to the first page..." + TextFormatter.RESET);
                                            break b;
                                        } else if (answer.equals("y") || answer.equals("yes")) {
                                            break c;
                                        }
                                    }
                                }
                            }
                        } else if (answer.equals("3")) {
                            System.out.println("Here's a list of your previous orders:");
                        } else {

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
                            OrderController.viewOrders();
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
                                            break l;
                                        }
                                    }
                                } else if (answer.equals("n") || answer.equals("no")) {
                                    System.out.println(TextFormatter.GREEN + "Going back to the first page..." + TextFormatter.GREEN);
                                    break k;
                                }
                            }
                        } else if (answer.equals("2")) {
                            storageController.printStorage();
                            o:
                            while (true) {
                                System.out.println("Would you like to add an ingredient to the storage? (y/n)");
                                answer = myScanner.nextLine().toLowerCase();
                                if (answer.equals("back")) {
                                    break o;
                                } else if (answer.equals("logout")) {
                                    break cc;
                                } else if (answer.equals("y") || answer.equals("yes")) {
                                    d:
                                    while (true) {
                                        System.out.println("What do you want to add? enter a ingredient.");
                                        answer = myScanner.nextLine().toLowerCase();
                                        if (answer.equals("back")) {
                                            break d;
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
                                                    break s;
                                                } else if (answer.equals("logout")) {
                                                    break cc;
                                                }
                                                try {
                                                    int amount = Integer.parseInt(answer);
                                                    storageController.increaseIngredient(ing, amount);
                                                    System.out.println(TextFormatter.GREEN + "The amount of " + ing.name() + " increased by " + amount + "." + TextFormatter.RESET);
                                                    break o;
                                                } catch (NumberFormatException e) {
                                                    System.out.println(TextFormatter.RED + "Your input should only contain numbers." + TextFormatter.RESET);
                                                }
                                            }
                                        }
                                    }
                                } else if (answer.equals("n") || answer.equals("no")) {
                                    System.out.println(TextFormatter.GREEN + "Going back to the first page..." + TextFormatter.GREEN);
                                    break o;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
