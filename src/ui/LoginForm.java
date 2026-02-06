package ui;

import java.util.Scanner;

import auth.AuthService;
import auth.UserSession;
import service.UserService;
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
        
    }

}
