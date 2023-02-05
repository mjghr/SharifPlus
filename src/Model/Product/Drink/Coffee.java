package Model.Product.Drink;

import Utils.Ingredient;

public class Coffee extends Drink {
    private final Ingredient[] ingredients = new Ingredient[]{Ingredient.Coffee};
    private final String name = "Coffee";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
