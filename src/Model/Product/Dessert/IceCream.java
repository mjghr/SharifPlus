package Model.Product.Dessert;

import Utils.Ingredient;

public class IceCream extends Dessert {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.IceCream};
    private String name = "ice-cream";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
