package ui;

import java.util.Map;
import java.util.Scanner;

import auth.AuthService;
import auth.UserSession;
import model.User;
import service.UserService;

public class ConsumerMenu implements Menu {

    Scanner scan = new Scanner(System.in);
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();
    private final MenuNavigator navigator = new MenuNavigator();

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

    public void profileOptions() {
        System.out.println("\nEscolha uma opção: ");
        System.out.println("1. Editar dados pessoais");
        System.out.println("2. Editar dados da conta");
        System.out.println("0. voltar");
    }

    public void profileFlow(){
        consumerProfile();
        navigator.navigate(
            this::profileOptions,
            Map.of(
                1, () -> System.out.println("Editar dados pessoais"),
                2, () -> System.out.println("Editar dados da conta"),
                0, () -> {}
            ));
    }

    @Override
    public void setPath() {
        navigator.navigate(
            this::showMenu, // <- Refêrencia ao método show menu 
        Map.of(// <- Cria map ja preenchido
            1, this::profileFlow, //<-- ação 1
            2, () -> { //() -> { ... } () = nenhum parametro, {} corpo do bloco run() Runnable na "mão"
                authService.logout();
                System.exit(0);
            }

        ));
    }
}
