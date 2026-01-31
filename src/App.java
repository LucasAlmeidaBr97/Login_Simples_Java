
import ui.MenuController;
import util.DataInitializer;

public class App {
    public static void main(String[] args) {
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.init();
        MenuController.start();
    }
}
