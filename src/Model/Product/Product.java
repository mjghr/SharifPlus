package Model.Product;

import Utils.Ingredient;

public abstract class Product {
    public abstract Ingredient[] getIngredient();

    public abstract String getName();

    public abstract boolean isAvailable();

    public abstract void setAvailable(boolean available);

    public abstract void printIngredient();
}
