package Model;

import Model.Product.Dessert.VanillaCake;
import Model.Product.Drink.*;
import Model.Product.Food.Pizza;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant extends Store {
    private HashMap<String, Product> restaurantProducts = new HashMap<>() {{
        put("pizza", new Pizza());
        put("burger", new Tea());
        put("steak", new HotChocolate());
        put("fried-chicken", new Soda());
        put("salad", new Water());
        put("french-fries", new VanillaCake());
        put("coffee", new Coffee());
        put("tea", new Tea());
        put("hot-chocolate", new HotChocolate());
        put("soda", new Soda());
        put("water", new Water());
    }};

    private ArrayList<String> restaurantProductsNames = new ArrayList<>(restaurantProducts.keySet());

    @Override
    public void getMenu() {
        System.out.println(ColorManager.BLUE + "Here's our Restaurant's menu:" + ColorManager.RESET);
        System.out.println(ColorManager.WHITE_BOLD + "Foods:" + ColorManager.RESET + "\n\t-Pizza\n\t-Burger\n\t-Steak\n\t-Fried chicken");
        System.out.println(ColorManager.WHITE_BOLD + "Appetizer:" + ColorManager.RESET + "\n\t-Salad\n\t-French fries");
        System.out.println(ColorManager.WHITE_BOLD + "Drinks:" + ColorManager.RESET + " \n\tHot:\n\t\t-Coffee\n\t\t-Tea\n\t\t-Hot chocolate\n\tcold:\n\t\t-Soda\n\t\t-Water");
    }

    @Override
    public void addOrder(User user, ArrayList<Product> productList) {
        Order order = new Order(user,productList);
        user.addUserOrder(order);
        Store.addOrder(order);
    }

    @Override
    public boolean isOrderValid(String str) {
        return restaurantProductsNames.contains(str);
    }

    @Override
    public String fix(String str) {

        if (str.contains("french fries"))
            str = str.replace("french fries", "french-fries");
        if (str.contains("fried chicken"))
            str = str.replace("fried chicken", "fried-chicken");
        if (str.contains("hot chocolate"))
            str = str.replace("hot chocolate", "hot-chocolate");

        return str;

    }

}
