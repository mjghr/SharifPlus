package Controller;

import Model.CoffeeShop;
import Utils.Ingredient;
import Utils.TextFormatter;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;


public class CoffeeShopController extends StoreController {
    private CoffeeShop model;

    public CoffeeShopController() {
        this.model = new CoffeeShop();
    }

    @Override
    public void getMenu() {
        System.out.println(TextFormatter.BLUE_BRIGHT + "\nHere's our CoffeeShop's menu:" + TextFormatter.RESET);
        System.out.println(TextFormatter.WHITE_BRIGHT + "\nDrinks:\n\tHot:\n\t\t-Coffee "
                + printAvailability("coffee") + "\n\t\t-Tea " + printAvailability("tea") + "\n\t\t-Hot chocolate "
                + printAvailability("hot-chocolate") + "\n\tcold:\n\t\t-Soda " + printAvailability("soda") + "\n\t\t-Water "
                + printAvailability("water") + TextFormatter.RESET);
        System.out.println(TextFormatter.WHITE_BRIGHT + "Desserts:\n\t-Chocolate cake " + printAvailability("chocolate-cake")
                + "\n\t-Vanilla cake " + printAvailability("vanilla-cake") + "\n\t-Ice cream " + printAvailability("ice-cream") + TextFormatter.RESET);
    }

    @Override
    public void addOrder() {

    }

    private String printAvailability(String str) {
        Product product = model.getCoffeeShopProducts().get(str);
        if (!product.isAvailable()) {
            return TextFormatter.RED + "(Not available)" + TextFormatter.RESET;
        }
        return "";
    }

    public void checkAvailability(HashMap<Ingredient, Integer> map) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product e : model.getCoffeeShopProducts().values()) {
            products.add(e);
        }

        setAvailability(map, products);
    }


    public HashMap<String, Product> getCoffeeShopProducts() {
        return model.getCoffeeShopProducts();
    }


    public boolean isOrderValid(String str) {
        return model.getCoffeeShopProductsNames().contains(str);
    }

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
