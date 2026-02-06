package ui;

import java.util.Map;
import java.util.Scanner;

import auth.UserSession;
import service.strategy.SelfRegistrationStrategy;
import service.strategy.UserRegistrationStrategy;

public class MainMenu implements Menu {

    Scanner scan = new Scanner(System.in);
    private final MenuNavigator navigator = new MenuNavigator();
    private final UserRegistrationStrategy registrationStrategy = new SelfRegistrationStrategy();

    @Override
    public void showMenu() {
        System.out.println("\n####### SISTEMA XPTO #######");
        System.out.println("1. Login");
        System.out.println("2. Criar Conta (Cadastro)");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    @Override
    public void setPath() {
        navigator.navigate(
                this::showMenu,
                Map.of(1, this::loginFlow,
                        2, this::signUpFlow,
                        0, () -> {
                            System.out.println("Até logo!");
                            System.exit(0);
                        }));
    }

    public void loginFlow() {
        System.out.println("Iniciando Login... ");
        LoginForm loginForm = new LoginForm();
        loginForm.showForm();

        if (UserSession.getInstance().isLogged()) {
            navigator.stop();
        }
    }

    public void signUpFlow() {
        System.out.println("Iniciando Cadastro... ");
        RegisterForm registerForm = new RegisterForm(registrationStrategy);
        registerForm.show();
    }

}
