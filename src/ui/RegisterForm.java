package ui;

import util.Validator;

import java.util.Scanner;

import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;
import service.strategy.UserRegistrationStrategy;

public class RegisterForm {
    private final UserRegistrationStrategy strategy;
    private final User user;
    private final Scanner scan = new Scanner(System.in);
    private final Validator validator = new Validator();

    public RegisterForm(UserRegistrationStrategy strategy) {
        this.strategy = strategy;
        this.user = new User();
    }

    public RegisterForm(UserRegistrationStrategy strategy, User existingUser) {
        this.strategy = strategy;
        this.user = existingUser; 
    }

    public void show() {
        System.out.println("\n--- FORMULÁRIO DE DADOS ---");
        
        System.out.print("Nome: ");
        this.user.setName(validator.validateName(scan.nextLine()));

        if (this.user.getEmail() == null) {
            System.out.print("E-mail: ");
            this.user.setEmail(validator.validateEmail(scan.nextLine()));        
        } else {
            System.out.println("E-mail: " + user.getEmail() + " [Identificado]");
        }

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        this.user.setBirthDate(validator.validateDate(scan.nextLine()));

        System.out.print("Defina sua senha: ");
        String password = validator.validatePassword(scan.nextLine());

        System.out.print("Confirme sua senha: ");
        String confirm = scan.nextLine();

        if (password.equals(confirm)) {
            strategy.register(this.user, password);
        } else {
            System.out.println("[ERRO] As senhas não conferem. Cadastro cancelado.");
        }

    }
}
