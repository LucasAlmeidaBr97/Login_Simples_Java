package ui;

import util.Validator;

import java.util.Scanner;

import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;
import service.strategy.UserRegistrationStrategy;

public class RegisterForm {
    private final UserRegistrationStrategy strategy;
    private final Scanner scan = new Scanner(System.in);
    private final Validator validator = new Validator();

    public RegisterForm(UserRegistrationStrategy strategy) {
        this.strategy = strategy;
    }

    public void show() {
        System.out.println("\n--- CADASTRO DE NOVO USUÁRIO ---");
        User newUser = new User();
        System.out.print("Nome: ");
        newUser.setName(validator.validateName(scan.nextLine()));

        System.out.print("E-mail: ");
        String email = validator.validateEmail(scan.nextLine());

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        newUser.setBirthDate(validator.validateDate(scan.nextLine()));

        System.out.print("Defina sua senha: ");
        String password = validator.validatePassword(scan.nextLine());

        System.out.print("Confirme sua senha: ");
        String confirm = scan.nextLine();

        newUser.setUserRole(UserRole.CONSUMER);
        newUser.setStatus(EntityStatus.ACTIVE);

        if (password.equals(confirm)) {
            strategy.register(newUser, password);
            System.out.println("Conta criada com sucesso! Faça login para continuar.");
        } else {
            System.out.println("[ERRO] As senhas não conferem. Cadastro cancelado.");
        }

    }
}
