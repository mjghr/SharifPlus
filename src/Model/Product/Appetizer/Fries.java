package Model.Product.Appetizer;

import Utils.Ingredient;

public class Fries extends Appetizer {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Potato, Ingredient.IceCream};
    private String name = "french-fries";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
