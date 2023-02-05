package Model.Product.Dessert;

import Utils.Ingredient;
import Model.Product.Product;

public abstract class Dessert extends Product {
    public abstract Ingredient[] getIngredient();
}
