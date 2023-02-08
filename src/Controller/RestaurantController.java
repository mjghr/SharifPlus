package Controller;

import Model.Product.Product;
import Utils.Ingredient;
import Utils.TextFormatter;
import Model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantController extends StoreController {

    private Restaurant model;

    public RestaurantController() {
        this.model = new Restaurant();
    }

    @Override
    public void getMenu() {
        System.out.println(TextFormatter.BLUE_BRIGHT + "\nHere's our Restaurant's menu:" + TextFormatter.RESET);
        System.out.println(TextFormatter.WHITE_BRIGHT+ "\nFoods:\n\t-Pizza "
                + printAvailability("pizza") + "\n\t-Burger " + printAvailability("burger")
                + "\n\t-Steak " + printAvailability("steak") + "\n\t-Fried chicken " + printAvailability("fried-chicken")+TextFormatter.RESET);
        System.out.println(TextFormatter.WHITE_BRIGHT + "Appetizer:\n\t-Salad " + printAvailability("salad")
                + "\n\t-French fries " + printAvailability("french-fries")+TextFormatter.RESET);
        System.out.println(TextFormatter.WHITE_BRIGHT + "Drinks:\n\tHot:\n\t\t-Coffee "
                + printAvailability("coffee") + "\n\t\t-Tea " + printAvailability("tea") + "\n\t\t-Hot chocolate "
                + printAvailability("hot-chocolate") + "\n\tcold:\n\t\t-Soda " + printAvailability("soda") + "\n\t\t-Water "
                + printAvailability("water")+TextFormatter.RESET);
    }

    @Override
    public void addOrder() {

    }

    private String printAvailability(String str) {
        Product product = model.getRestaurantProducts().get(str);
        if (!product.isAvailable()) {
            return TextFormatter.RED + "(Not available)" + TextFormatter.RESET;
        }
        return "";
    }

    public void checkAvailability(HashMap<Ingredient, Integer> map) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product e : model.getRestaurantProducts().values()) {
            products.add(e);
        }

        setAvailability(map, products);
    }


    public boolean isOrderValid(String str) {
        return model.getRestaurantProductsNames().contains(str);
    }

    public String fix(String str) {

        if (str.contains("french fries"))
            str = str.replace("french fries", "french-fries");
        if (str.contains("fried chicken"))
            str = str.replace("fried chicken", "fried-chicken");
        if (str.contains("hot chocolate"))
            str = str.replace("hot chocolate", "hot-chocolate");

        return str;

    }

    public HashMap<String, Product> getRestaurantProducts() {
        return model.getRestaurantProducts();
    }

}
