package Model.Product.Dessert;

import Utils.Ingredient;

public class VanillaCake extends Dessert {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Sugar, Ingredient.Vanilla};
    private String name = "vanilla-cake";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
