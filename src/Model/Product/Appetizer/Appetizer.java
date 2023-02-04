package Model.Product.Appetizer;

import Model.Ingredient;
import Model.Product.Product;

public abstract class Appetizer extends Product {
    public abstract Ingredient[] getIngredient();
}
