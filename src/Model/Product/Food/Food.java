package Model.Product.Food;

import Utils.Ingredient;
import Model.Product.Product;

import java.util.ArrayList;


public abstract class Food extends Product {
    public abstract Ingredient[] getIngredient();

    public abstract String getName();

    public abstract boolean isAvailable();
    public abstract void setAvailable(boolean available);
    public abstract void printIngredient();

}
