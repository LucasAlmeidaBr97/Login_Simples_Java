package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import service.strategy.FormStrategy;
import service.strategy.SelfRegistrationStrategy;

public class MainMenu implements Menu {

    Scanner scan = new Scanner(System.in);

    @Override
    public void showMenu() {
        System.out.println("####################################");
        System.out.println("            Seja Bem Vindo!");
        System.out.println("Escolha uma opção");
        System.out.println("1. Cadastrar | 2. Login | 3. Sair");
    }

    public void setPath() {
        int path = 0;
        while (path != 3) {
            showMenu();
            try {
                path = scan.nextInt();
                switch (path) {
                    case 1 -> {
                        System.out.println("Iniciando Cadastro... ");
                        FormStrategy strategy = new SelfRegistrationStrategy();
                        RegisterForm form = new RegisterForm(strategy);
                        form.showForm();
                    }
                    case 2 -> {
                        System.out.println("Iniciando Login... ");
                        LoginForm loginForm = new LoginForm();
                        loginForm.showForm();
                    }
                    case 3 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite uma das três opções.");
                scan.nextLine();
            }
        }
    }

}
