package Model.Product.Appetizer;

import Utils.Ingredient;

public class Salad extends Appetizer {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Meat, Ingredient.Bread, Ingredient.Vegetable};
    private String name = "Salad";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
