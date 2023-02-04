package Model.Product.Drink;

import Model.DrinkType;
import Model.Ingredient;

public class Tea extends Drink {
    private DrinkType type = DrinkType.Hot;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Tea};
    private String name = "Tea";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
