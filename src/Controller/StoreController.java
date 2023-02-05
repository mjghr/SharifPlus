package Controller;

import Model.Store;


public abstract class StoreController {
    private static Store model = new Store();

    public abstract void getMenu();
    public abstract boolean isOrderValid(String str);

    public abstract String fix(String Str);

}
