package Model.Product.Appetizer;

import Utils.Ingredient;
import Model.Product.Product;

public abstract class Appetizer extends Product {
    private Ingredient[] ingredients;

    public abstract String getName();
    public Ingredient[] getIngredient() {
        return ingredients;
    }

    public abstract void setAvailable(boolean available);

    public abstract void printIngredient();
}
