package Model.Product.Food;

import Model.Ingredient;

public class Steak extends Food {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Meat, Ingredient.Sugar};
    private String name = "Steak";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
