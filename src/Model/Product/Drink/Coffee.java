package Model.Product.Drink;

import Model.DrinkType;
import Model.Ingredient;

public class Coffee extends Drink {
    private final Ingredient[] ingredients = new Ingredient[]{Ingredient.Coffee};
    private final String name = "Coffee";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
