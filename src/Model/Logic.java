package Model;

import Model.Product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Logic {
    private static User currentUser;

    private static final Restaurant restaurant = new Restaurant();
    private static final CoffeeShop coffeeShop = new CoffeeShop();
    private static final Storage storage = new Storage();
    private static Scanner myScanner = new Scanner(System.in);

    public Logic() {
        System.out.println(ColorManager.GREEN_BOLD + "Welcome to SharifPlus!" + ColorManager.RESET);
        System.out.println("\n" + ColorManager.YELLOW_BOLD + "-You can always go back to the previous page by typing \"back\"." + ColorManager.RESET);
        System.out.println(ColorManager.YELLOW_BOLD + "-You can always logout of your account by typing \"logout\" after you login." + ColorManager.RESET + "\n");
        User.setUsers(JsonManager.readArrayFromJson("users.json"));

        while (true) {
            System.out.println("Already have an account? (y/n)");
            String answer = myScanner.nextLine().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                User.login(myScanner);
            } else if (answer.equals("n") || answer.equals("no")) {
                User.createAccount(myScanner);
            }

            if (currentUser.getType().equals("customer")) {
                bb:
                while (true) {
                    currentUser.printUserOptions();
                    answer = myScanner.nextLine();
                    if (answer.equals("back")) {
                        System.out.println("You're logging out of your account...");
                        break bb;
                    } else if (answer.equals("logout")) {
                        System.out.println("You're logging out of your account...");
                        break bb;
                    } else if (answer.equals("1")) {
                        restaurant.getMenu();
                        System.out.println(ColorManager.BLUE + "Enter the name of the product you want to buy." + ColorManager.RESET);
                        while (true) {
                            break;
                        }
                    } else if (answer.equals("2")) {

                        coffeeShop.getMenu();
                        b:
                        while (true) {
                            System.out.println(ColorManager.BLUE + "Enter the name of the product you want to buy." + ColorManager.RESET);
                            answer = myScanner.nextLine().toLowerCase();
                            if (answer.equals("back"))
                                break b;
                            else if (answer.equals("logout")) {
                                break bb;
                            }
                            ArrayList<String> orders = new ArrayList<>(Arrays.asList(coffeeShop.fix(answer).split(" ")));
                            ArrayList<Product> productList = new ArrayList<>();
                            boolean gg = true;
                            n:
                            for (int i = 0; i < orders.size(); i++) {
                                if (!coffeeShop.isOrderValid(orders.get(i))) {
                                    System.out.println(ColorManager.RED + "invalid product names. please try again." + ColorManager.RESET);
                                    gg = false;
                                    break n;
                                } else {
                                    Product product = coffeeShop.getCoffeeShopProducts().get(orders.get(i));
                                    productList.add(product);
                                    Ingredient[] ingredients = product.getIngredient();
                                    for (int j = 0; j < ingredients.length; j++) {
                                        if (storage.getItems().get(ingredients[j]) == 0) {
                                            System.out.println(ColorManager.RED + "I'm afraid we don't have enough items for this product. try ordering again." + ColorManager.RESET);
                                            gg = false;
                                            break n;
                                        }
                                    }
                                }
                            }
                            if (gg) {
                                coffeeShop.addOrder(currentUser, productList);
                                c:
                                while (true) {
                                    System.out.println("would you like to order anything else? (y/n)");
                                    answer = myScanner.nextLine();
                                    if (answer.equals("back"))
                                        break c;
                                    else if (answer.equals("logout")) {
                                        break bb;
                                    }
                                    if (answer.equals("n") || answer.equals("no")) {
                                        System.out.println(ColorManager.GREEN_BOLD_BRIGHT + "Finished ordering." + ColorManager.RESET);
                                        System.out.println(ColorManager.GREEN_BOLD_BRIGHT + "going back to the first page..." + ColorManager.RESET);
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
            } else if (currentUser.getType().equals("admin")) {
                cc:
                while (true) {
                    currentUser.printUserOptions();
                    answer = myScanner.nextLine().toLowerCase();
                    if (answer.equals("back")) {
                        System.out.println("You're logging out of your account...");
                        break cc;
                    } else if (answer.equals("logout")) {
                        System.out.println("You're logging out of your account...");
                        break cc;
                    } else if (answer.equals("1")) {
                        Store.viewOrders();
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
                                    Order order = Store.getOrderById(answer);
                                    if (order == null) {
                                        System.out.println(ColorManager.RED + "No order found with the id given. please try again." + ColorManager.RESET);
                                    } else {
                                        Store.removeOrder(order);
                                        break l;
                                    }
                                }
                            } else if (answer.equals("n") || answer.equals("no")) {
                                System.out.println(ColorManager.GREEN + "Going back to the first page..." + ColorManager.GREEN);
                                break k;
                            }
                        }
                    } else if (answer.equals("2")) {
                        storage.printStorage();
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
                                    Ingredient ing = storage.getIngredientByName(answer);
                                    if (ing == null) {
                                        System.out.println(ColorManager.RED + "I'm afraid we don't have such ingredient in our storage, try again." + ColorManager.RESET);
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
                                                storage.increaseIngredient(ing, amount);
                                                System.out.println(ColorManager.GREEN + "The amount of " + ing.name() + " increased by " + amount + "." + ColorManager.RESET);
                                                break o;
                                            } catch (NumberFormatException e) {
                                                System.out.println(ColorManager.RED + "Your input should only contain numbers." + ColorManager.RESET);
                                            }
                                        }
                                    }
                                }
                            } else if (answer.equals("n") || answer.equals("no")) {
                                System.out.println(ColorManager.GREEN + "Going back to the first page..." + ColorManager.GREEN);
                                break o;
                            }
                        }
                    }
                }

            }
        }
    }


    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}