package Model;

import Model.Product.Dessert.ChocolateCake;
import Model.Product.Dessert.IceCream;
import Model.Product.Dessert.VanillaCake;
import Model.Product.Drink.*;
import Model.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeShop {
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


    public HashMap<String, Product> getCoffeeShopProducts() {
        return coffeeShopProducts;
    }

    public void setCoffeeShopProducts(HashMap<String, Product> coffeeShopProducts) {
        this.coffeeShopProducts = coffeeShopProducts;
    }

    public ArrayList<String> getCoffeeShopProductsNames() {
        return coffeeShopProductsNames;
    }

    public void setCoffeeShopProductsNames(ArrayList<String> coffeeShopProductsNames) {
        this.coffeeShopProductsNames = coffeeShopProductsNames;
    }
}
