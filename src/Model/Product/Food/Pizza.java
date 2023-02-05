package Model.Product.Food;
import Utils.Ingredient;

public class Pizza extends Food {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Meat, Ingredient.Bread, Ingredient.Cheese};
    private String name = "Pizza";

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }
}
