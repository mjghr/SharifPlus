package Model.Product.Food;

import Utils.Ingredient;

public class FriedChicken extends Food {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Chicken, Ingredient.Cheese, Ingredient.Potato};
    private String name = "fried-chicken";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
