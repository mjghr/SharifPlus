package Controller;

import Model.CoffeeShop;
import Utils.TextFormatter;
import Model.Product.Product;

import java.util.HashMap;


public class CoffeeShopController extends StoreController {
    private CoffeeShop model;

    public CoffeeShopController(CoffeeShop model) {
        this.model = model;
    }

    @Override
    public void getMenu() {
        System.out.println(TextFormatter.BLUE + "Here's our CoffeeShop's menu:" + TextFormatter.RESET);
        System.out.println("Drinks:\n\tHot:\n\t\t1-Coffee\n\t\t2-Tea\n\t\t3-Hot chocolate\n\tcold:\n\t\t4-Soda\n\t\t5-Water");
        System.out.println("Desserts:\n\t6-Chocolate cake\n\t7-Vanilla cake\n\t8-Ice cream");
    }

    public HashMap<String, Product> getCoffeeShopProducts() {
        return model.getCoffeeShopProducts();
    }

    @Override
    public boolean isOrderValid(String str) {
        return model.getCoffeeShopProductsNames().contains(str);
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

}
