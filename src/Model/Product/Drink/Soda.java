package Model.Product.Drink;

import Utils.DrinkType;
import Utils.Ingredient;

public class Soda extends Drink{
    private DrinkType type = DrinkType.Cold;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Soda};
    private String name = "Soda";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
