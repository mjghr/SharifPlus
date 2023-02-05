package Model.Product.Food;

import Utils.Ingredient;

public class Burger extends Food {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Meat, Ingredient.Bread, Ingredient.Vegetable};
    private String name = "Burger";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
