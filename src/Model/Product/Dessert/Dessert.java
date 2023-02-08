package Model.Product.Dessert;

import Utils.Ingredient;
import Model.Product.Product;

public abstract class Dessert extends Product {
    public abstract Ingredient[] getIngredient();

    public abstract String getName();

    public abstract boolean isAvailable();
    public abstract void setAvailable(boolean available);
    public abstract void printIngredient();
}
