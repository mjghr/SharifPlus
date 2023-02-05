package Model.Product.Drink;

import Utils.DrinkType;
import Utils.Ingredient;

public class HotChocolate extends Drink {
    private DrinkType type = DrinkType.Hot;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Water, Ingredient.Sugar, Ingredient.Chocolate};
    private String name = "hot-chocolate";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
