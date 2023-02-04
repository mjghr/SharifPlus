package Model;

public class Customer extends User {
    public Customer(String name, String type, String password) {
        super(name, type, password);
    }

    public void printUserOptions() {
        super.printUserOptions();
        System.out.println(ColorManager.YELLOW_BOLD + "\t-enter our coffeeShop or restaurant:" + ColorManager.RESET);
        System.out.println(ColorManager.YELLOW_BOLD + "\t\t1-Restaurant" + ColorManager.RESET);
        System.out.println(ColorManager.YELLOW_BOLD + "\t\t2-CoffeeShop" + ColorManager.RESET);
        System.out.println(ColorManager.YELLOW_BOLD + "\t3-Check your previous orders." + ColorManager.RESET);
    }

}

