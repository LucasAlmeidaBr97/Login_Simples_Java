package ui;

import auth.UserSession;

public class MenuController {

    public static void start() {
        while (true) {
            if (!UserSession.getInstance().isLogged()) {
                new MainMenu().setPath();
                if (!UserSession.getInstance().isLogged()) {
                    break; 
                }
            } else {
                Menu menu = FactoryMenu.createMenu(
                    UserSession.getInstance().getUserRole()
                );
                menu.setPath();
            }
        }
        System.out.println("Aplicação encerrada.");
    }
}
