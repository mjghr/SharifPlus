package Controller;

import Utils.TextFormatter;
import Model.Restaurant;

public class RestaurantController extends StoreController {

    private Restaurant model;

    public RestaurantController(Restaurant model) {
        this.model = model;
    }

    @Override
    public void getMenu() {
        System.out.println(TextFormatter.BLUE + "Here's our Restaurant's menu:" + TextFormatter.RESET);
        System.out.println(TextFormatter.WHITE_BOLD + "Foods:" + TextFormatter.RESET + "\n\t-Pizza\n\t-Burger\n\t-Steak\n\t-Fried chicken");
        System.out.println(TextFormatter.WHITE_BOLD + "Appetizer:" + TextFormatter.RESET + "\n\t-Salad\n\t-French fries");
        System.out.println(TextFormatter.WHITE_BOLD + "Drinks:" + TextFormatter.RESET + " \n\tHot:\n\t\t-Coffee\n\t\t-Tea\n\t\t-Hot chocolate\n\tcold:\n\t\t-Soda\n\t\t-Water");
    }

    @Override
    public boolean isOrderValid(String str) {
        return model.getRestaurantProductsNames().contains(str);
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
