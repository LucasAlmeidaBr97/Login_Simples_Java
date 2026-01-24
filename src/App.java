import ui.MainMenu;
import util.DataInitializer;

public class App {
    public static void main(String[] args) {
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.init();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setPath();
    }
}
