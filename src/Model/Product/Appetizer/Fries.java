package Model.Product.Appetizer;

import Utils.Ingredient;

import java.sql.Array;
import java.util.Arrays;

public class Fries extends Appetizer {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Potato, Ingredient.IceCream};
    private String name = "french-fries";
    private boolean isAvailable = true;


    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public void printIngredient() {
        System.out.println("This product contains of:");
        for (int i = 0; i < ingredients.length; i++) {
            System.out.print(ingredients[i].name() + " ");
        }
        System.out.println();
    }
}
