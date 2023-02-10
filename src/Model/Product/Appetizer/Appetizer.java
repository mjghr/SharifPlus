package Model.Product.Appetizer;

import Utils.Ingredient;
import Model.Product.Product;

public abstract class Appetizer extends Product {
    public abstract Ingredient[] getIngredient();

    public abstract String getName();

    public abstract boolean isAvailable();
    public abstract void setAvailable(boolean available);
    public abstract void printIngredient();
}
