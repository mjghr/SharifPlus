package Model.Product.Drink;

import Utils.DrinkType;
import Utils.Ingredient;

public class Soda extends Drink {
    private DrinkType type = DrinkType.Cold;
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.Soda};
    private String name = "Soda";

    private boolean isAvailable = true;

    @Override
    public Ingredient[] getIngredient() {
        return ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
    @Override
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    @Override
    public void printIngredient() {
        System.out.println("This product contains of:");
        for (int i = 0; i < ingredients.length; i++) {
            System.out.print(ingredients[i].name() + " ");
        }
        System.out.println();
    }
}
