package Controller;

import Utils.Ingredient;
import Model.Storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StorageController {
    private Storage model;

    public StorageController(Storage model) {
        this.model = model;
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

    public void increaseIngredient(Ingredient ing, int amount){
        model.getItems().put(ing, model.getItems().get(ing) + amount);
    }
    public void decreaseIngredient(Ingredient ing, int amount){
        model.getItems().put(ing, model.getItems().get(ing) - amount);
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
