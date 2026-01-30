package ui;

import java.util.Scanner;

import auth.AuthService;
import util.Validator;

public class LoginForm {
    Validator validator = new Validator();
    Scanner scan = new Scanner(System.in);
    private final AuthService authService = new AuthService();

    public LoginForm() {
        
    }

    public void showForm(){
        System.out.println("####################################");
        System.out.println("\n=== FORMULÁRIO DE Login ===");
        System.out.println("Digite seu email: ");
        String email = validator.validateEmail(scan.nextLine());
        System.out.println("Digite sua senha: ");
        String password = validator.validatePassword(scan.nextLine());
        authService.login(email, password);
        authService.logout();

    }
    
}
