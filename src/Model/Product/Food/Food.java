package Model.Product.Food;

import Utils.Ingredient;
import Model.Product.Product;


public abstract class Food extends Product {
    public abstract Ingredient[] getIngredient();

}
