package Model.Product.Drink;

import Model.DrinkType;
import Model.Ingredient;

public class Soda extends Drink{
    private DrinkType type = DrinkType.Cold;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Soda};
    private String name = "Soda";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
