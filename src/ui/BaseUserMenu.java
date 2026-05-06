package ui;

import java.util.Map;

import auth.UserSession;
import model.User;
import service.UserService;

public abstract class BaseUserMenu implements Menu {

    protected final UserService userService = new UserService();
    protected final MenuNavigator navigator = new MenuNavigator();

    @Override
    public void showMenu(){
        System.out.println("\n####################################");
        System.out.println("            Seja Bem Vindo!");
        System.out.println("Escolha uma opção");    
        printOptions();
    }


    protected abstract void printOptions();
    protected abstract Map<Integer, Runnable> getActions();


    @Override
    public void setPath(){
        navigator.navigate(this::showMenu, getActions());
    }

     public void showProfile() {
        String email = UserSession.getInstance().getEmail();
        User user = userService.getUser(email);

        System.out.println("--------------------------------------");
        System.out.println("Nome: " + user.getName() + " | Nascimento: " + user.getBirthDate());
        System.out.println("E-mail: " + user.getEmail() + " | Status: " + user.getStatus());
        System.out.println("--------------------------------------");
    }

}
