import bootstrap.DataInitializer;
import ui.MainMenu;

public class App {
    public static void main(String[] args) {
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.init();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setPath();
    }
}
