package Controller;

import Model.Product.Product;
import Model.Store;
import Utils.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class StoreController {
    private static Store model = new Store();

    public abstract void getMenu();
    public abstract void addOrder();

    static void setAvailability(HashMap<Ingredient, Integer> map, ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            Ingredient[] ingredients = products.get(i).getIngredient();
            for (int j = 0; j < ingredients.length; j++) {
                if (map.get(ingredients[j]) <= 0) {
                    products.get(i).setAvailable(false);
                    break;
                } else {
                    products.get(i).setAvailable(true);
                }
            }
        }
    }

}
