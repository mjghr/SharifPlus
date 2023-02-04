package Model.Product.Food;

import Model.Ingredient;
import Model.Product.Product;


public abstract class Food extends Product {
    public abstract Ingredient[] getIngredient();

}
