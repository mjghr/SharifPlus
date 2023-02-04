package Model.Product.Drink;

import Model.Ingredient;
import Model.Product.Product;


public abstract class Drink extends Product {
    public abstract Ingredient[] getIngredient();
}
