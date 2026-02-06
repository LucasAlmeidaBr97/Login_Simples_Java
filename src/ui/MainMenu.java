package ui;

import java.util.Map;
import java.util.Scanner;

import auth.UserSession;
import service.strategy.UserSaveStrategy;
import service.strategy.SelfRegistrationStrategy;

public class MainMenu implements Menu {

    Scanner scan = new Scanner(System.in);
    private final MenuNavigator navigator = new MenuNavigator();

    @Override
    public void showMenu() {
        System.out.println("\n####################################");
        System.out.println("          Escolha uma opção");
        System.out.println("1. Cadastrar | 2. Login | 0. Sair");
    }

    @Override
    public void setPath() {
        navigator.navigate(
                this::showMenu,
                Map.of(1, this::register,
                        2, this::login,
                        0, () -> {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }));
    }

    public void login() {
        System.out.println("Iniciando Login... ");
        LoginForm loginForm = new LoginForm();
        loginForm.showForm();

        if (UserSession.getInstance().isLogged()) {
            navigator.stop();
        }
    }

    public void register() {
        System.out.println("Iniciando Cadastro... ");
        UserSaveStrategy strategy = new SelfRegistrationStrategy();
        RegisterForm form = new RegisterForm(strategy);
        form.showForm();
    }

}
