package Model.Product.Dessert;

import Model.Ingredient;

public class ChocolateCake extends Dessert {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Chocolate, Ingredient.Sugar};
    private String name = "chocolate-cake";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
