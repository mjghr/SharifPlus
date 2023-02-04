package Model.Product.Drink;

import Model.DrinkType;
import Model.Ingredient;

public class Water extends Drink {
    private DrinkType type = DrinkType.Cold;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Water};
    private String name = "Water";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
