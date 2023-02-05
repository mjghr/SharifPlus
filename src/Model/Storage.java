package Model;

import Utils.Ingredient;

import java.util.HashMap;
import java.util.Random;

public class Storage {
    private HashMap<Ingredient, Integer> items = new HashMap<>() {{
        put(Ingredient.Coffee, randomNumber());
        put(Ingredient.Meat, randomNumber());
        put(Ingredient.Potato, randomNumber());
        put(Ingredient.Chocolate, randomNumber());
        put(Ingredient.Chicken, randomNumber());
        put(Ingredient.Water, randomNumber());
        put(Ingredient.Flour, randomNumber());
        put(Ingredient.Beans, randomNumber());
        put(Ingredient.Bread, randomNumber());
        put(Ingredient.Cheese, randomNumber());
        put(Ingredient.Vegetable, randomNumber());
        put(Ingredient.Vanilla, randomNumber());
        put(Ingredient.Tea, randomNumber());
        put(Ingredient.IceCream, randomNumber());
        put(Ingredient.Sugar, randomNumber());
        put(Ingredient.Egg, randomNumber());
        put(Ingredient.Soda, randomNumber());

    }};

    private static int randomNumber() {
        Random random = new Random();
        int num = random.nextInt(10);
        if (num == 0)
            return 1;
        return num;
    }

    public HashMap<Ingredient, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<Ingredient, Integer> items) {
        this.items = items;
    }
}
