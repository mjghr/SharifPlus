package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    public boolean isIngredientAvailable(Ingredient ing) {
        return false;
    }

    public HashMap<Ingredient, Integer> getItems() {
        return items;
    }

    public void printStorage() {
        for (Map.Entry<Ingredient, Integer> item :
                items.entrySet()) {
            System.out.println(item.getKey().name() + ": " + item.getValue());
        }
    }

    public void increaseIngredient(Ingredient ing, int amount){
        items.put(ing, items.get(ing) + amount);
    }

    public Ingredient getIngredientByName(String nam){
        for (Ingredient ingredient : Ingredient.values()) {
            if (ingredient.name().toLowerCase().equals(nam)) {
                return ingredient;
            }
        }
        return null;
    }

    private static int randomNumber() {
        Random random = new Random();
        int num = random.nextInt(10);
        if (num == 0)
            return 1;
        return num;
    }
}
