package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import auth.AuthService;
import auth.UserSession;
import model.User;
import service.UserService;

public class ConsumerMenu implements Menu {

    Scanner scan = new Scanner(System.in);
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();

    @Override
    public void showMenu() {
        System.out.println("\n####################################");
        System.out.println("            Seja Bem Vindo!");
        System.out.println("Escolha uma opção");
        System.out.println("1. Meu Perfil | 2. Log-out |");
    }

    public void consumerProfile() {
        System.out.println("\n####################################");
        System.out.println("      Informações do seu perfil");
        System.out.println("Você pode adicionar, alterar ou corrigir suas informações \npessoais e os dados da conta.");
        System.out.println("--------------------------------------");
        String email = UserSession.getInstance().getEmail();
        User user = (userService.getUser(email));
        System.out.println("Dados Pessoais - Nome: " + user.getName() + ". Aniversário: " + user.getBirthDate());
        System.out.println("--------------------------------------");
        System.out.println("Dados da Conta - E-mail: " + user.getEmail() + ". Status: " + user.getStatus());
    }

    @Override
    public void setPath() {
        int path = 0;
        while (UserSession.getInstance().isLogged()) {
            showMenu();
            try {
                path = scan.nextInt();
                scan.nextLine();
                switch (path) {
                    case 1 -> {
                        consumerProfile();
                    }
                    case 2 -> {
                        authService.logout();
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite uma das três opções.");
                scan.nextLine();
            }
        }
    }
}
