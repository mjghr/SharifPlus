package Model;

import Model.Product.Dessert.ChocolateCake;
import Model.Product.Dessert.IceCream;
import Model.Product.Dessert.VanillaCake;
import Model.Product.Drink.*;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeShop extends Store {
    private HashMap<String, Product> coffeeShopProducts = new HashMap<>() {{
        put("tea", new Tea());
        put("coffee", new Coffee());
        put("hot-chocolate", new HotChocolate());
        put("soda", new Soda());
        put("water", new Water());
        put("vanilla-cake", new VanillaCake());
        put("chocolate-cake", new ChocolateCake());
        put("ice-cream", new IceCream());
    }};

    private ArrayList<String> coffeeShopProductsNames = new ArrayList<>(coffeeShopProducts.keySet());


    @Override
    public void getMenu() {
        System.out.println(ColorManager.BLUE + "Here's our CoffeeShop's menu:" + ColorManager.RESET);
        System.out.println("Drinks:\n\tHot:\n\t\t1-Coffee\n\t\t2-Tea\n\t\t3-Hot chocolate\n\tcold:\n\t\t4-Soda\n\t\t5-Water");
        System.out.println("Desserts:\n\t6-Chocolate cake\n\t7-Vanilla cake\n\t8-Ice cream");
    }

    @Override
    public void addOrder(User user, ArrayList<Product> productList) {
        Order order = new Order(user,productList);
        user.addUserOrder(order);
        Store.addOrder(order);
    }

    @Override
    public boolean isOrderValid(String str) {
        return coffeeShopProductsNames.contains(str);
    }

    @Override
    public String fix(String str) {

        if (str.contains("hot chocolate"))
            str = str.replace("hot chocolate", "hot-chocolate");
        if (str.contains("chocolate cake"))
            str = str.replace("chocolate cake", "chocolate-cake");
        if (str.contains("ice cream"))
            str = str.replace("ice cream", "ice-cream");
        if (str.contains("vanilla cake"))
            str = str.replace("vanilla cake", "vanilla-cake");

        return str;

    }

    public HashMap<String, Product> getCoffeeShopProducts() {
        return coffeeShopProducts;
    }
}
