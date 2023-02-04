package Model;

public class Admin extends User{
    public Admin(String name, String type, String password) {
        super(name, type, password);
    }

    @Override
    public void printUserOptions() {
        super.printUserOptions();
        System.out.println(ColorManager.YELLOW_BOLD + "\t1-View all orders submitted." + ColorManager.RESET);
        System.out.println(ColorManager.YELLOW_BOLD + "\t\tyou can remove an order." + ColorManager.RESET);
        System.out.println(ColorManager.YELLOW_BOLD + "\t2-View the shop's storage." + ColorManager.RESET);
    }
}
