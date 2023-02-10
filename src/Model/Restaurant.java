package Model;

import Model.Product.Appetizer.Fries;
import Model.Product.Appetizer.Salad;
import Model.Product.Dessert.VanillaCake;
import Model.Product.Drink.*;
import Model.Product.Food.Burger;
import Model.Product.Food.FriedChicken;
import Model.Product.Food.Pizza;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
    private HashMap<String, Product> restaurantProducts = new HashMap<>() {{
        put("pizza", new Pizza());
        put("burger", new Burger());
        put("steak", new HotChocolate());
        put("fried-chicken", new FriedChicken());
        put("salad", new Salad());
        put("french-fries", new Fries());
        put("coffee", new Coffee());
        put("tea", new Tea());
        put("hot-chocolate", new HotChocolate());
        put("soda", new Soda());
        put("water", new Water());
    }};

    private ArrayList<String> restaurantProductsNames = new ArrayList<>(restaurantProducts.keySet());

    public HashMap<String, Product> getRestaurantProducts() {
        return restaurantProducts;
    }

    public void setRestaurantProducts(HashMap<String, Product> restaurantProducts) {
        this.restaurantProducts = restaurantProducts;
    }

    public ArrayList<String> getRestaurantProductsNames() {
        return restaurantProductsNames;
    }

    public void setRestaurantProductsNames(ArrayList<String> restaurantProductsNames) {
        this.restaurantProductsNames = restaurantProductsNames;
    }
}
