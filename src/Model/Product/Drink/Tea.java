package Model.Product.Drink;

import Utils.DrinkType;
import Utils.Ingredient;

public class Tea extends Drink {
    private DrinkType type = DrinkType.Hot;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Tea};
    private String name = "Tea";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
