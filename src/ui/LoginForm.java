package ui;

import java.util.Scanner;

import auth.AuthService;
import model.User;
import service.UserService;
import service.strategy.ReactivationStrategy;
import service.strategy.UserRegistrationStrategy;
import util.Validator;

public class LoginForm {
    private final Validator validator = new Validator();
    private final Scanner scan = new Scanner(System.in);
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();

    public LoginForm() {

    }

    public void show() {
        System.out.println("\n####################################");
        System.out.println("===== Entrar com email e senha =====");

        System.out.println("Digite seu email: ");
        String email = validator.validateEmail(scan.nextLine());

        System.out.println("Digite sua senha: ");
        String password = validator.validatePassword(scan.nextLine());

        AuthService.LoginStatus status = authService.authenticate(email, password);
        handleLoginStatus(status, email);

    }

    private void handleLoginStatus(AuthService.LoginStatus status, String email) {
        switch (status) {
            case SUCCESS:
                System.out.println("\n[SUCESSO] Bem-vindo ao sistema!");
                break;

            case INACTIVE:
                handleInactivity(email);
                break;

            case LOCKED:
                System.out.println("\n[ERRO] Esta conta está BLOQUEADA. Contate um administrador.");
                break;

            case INVALID_PASSWORD:
                System.out.println("\n[ERRO] Senha incorreta.");
                break;

            case USER_NOT_FOUND:
                System.out.println("\n[ERRO] E-mail não cadastrado.");
        }
    }

    private void handleInactivity(String email) {
        System.out.println("\n[AVISO] Sua conta está DESATIVADA.");
        System.out.println("Deseja reativá-la agora? (1. Sim | 0. Voltar)");

        String op = scan.nextLine();
        if (op.equals("1")) {
            User existingUser = userService.getUser(email);

            UserRegistrationStrategy strategy = new ReactivationStrategy();
            RegisterForm registerForm = new RegisterForm(strategy, existingUser);

            registerForm.show();
        }
    }
}
