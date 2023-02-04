package Model.Product.Dessert;

import Model.Ingredient;
import Model.Product.Product;

public abstract class Dessert extends Product {
    public abstract Ingredient[] getIngredient();
}
