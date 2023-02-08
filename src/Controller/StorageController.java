package Controller;

import Utils.Ingredient;
import Model.Storage;
import Utils.TextFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StorageController {
    private Storage model;

    public StorageController() {
        this.model = new Storage();
    }

    public boolean isIngredientAvailable(Ingredient ing) {
        return false;
    }

    public HashMap<Ingredient, Integer> getStorageItems() {
        return model.getItems();
    }

    public void printStorage() {
        for (Map.Entry<Ingredient, Integer> item :
                model.getItems().entrySet()) {
            System.out.println(item.getKey().name() + ": " + item.getValue());
        }
    }

    public boolean increaseIngredient(Ingredient ing, int amount) {

        if (amount <= 0) {
            System.out.println(TextFormatter.RED + "The amount can't be zero or negative." + TextFormatter.RESET);
            return false;
        }

        model.getItems().put(ing, model.getItems().get(ing) + amount);
        return true;
    }

    public void decreaseIngredient(Ingredient ing, int amount) {
        HashMap<Ingredient,Integer> ings = model.getItems();
        if ((ings.get(ing) - amount) < 0) {
            ings.put(ing, ings.get(ing) - 0);
        }
        ings.put(ing, ings.get(ing) - amount);
    }

    public boolean increaseAllIngredients(int amount) {
        if (amount <= 0) {
            System.out.println(TextFormatter.RED + "The amount can't be zero or negative." + TextFormatter.RESET);
            return false;
        }

        for (Map.Entry<Ingredient, Integer> item :
                model.getItems().entrySet()) {
            model.getItems().put(item.getKey(), item.getValue() + amount);
        }
        return true;
    }

    public void decreaseAllIngredients(int amount) {
        for (Map.Entry<Ingredient, Integer> item :
                model.getItems().entrySet()) {
            if ((item.getValue() - amount) < 0) {
                model.getItems().put(item.getKey(), 0);
                continue;
            }
            model.getItems().put(item.getKey(), item.getValue() - amount);
        }
    }

    public Ingredient getIngredientByName(String nam) {
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

    public void setItems(HashMap<Ingredient, Integer> items) {
        model.setItems(items);
    }

}
