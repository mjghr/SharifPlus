package Model.Product.Dessert;

import Utils.Ingredient;

public class IceCream extends Dessert {
    private Ingredient[] ingredients = new Ingredient[]{Ingredient.IceCream};
    private String name = "ice-cream";

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
