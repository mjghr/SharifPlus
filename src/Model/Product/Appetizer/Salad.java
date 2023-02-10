package Model.Product.Appetizer;

import Utils.Ingredient;

public class Salad extends Appetizer {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Bread, Ingredient.Vegetable};
    private String name = "Salad";

    private boolean isAvailable = true;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
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
