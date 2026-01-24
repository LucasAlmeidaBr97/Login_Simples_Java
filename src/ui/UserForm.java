package ui;

import util.Validator;

import java.time.LocalDate;
import java.util.Scanner;

import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;
import service.strategy.FormStrategy;

public class UserForm {
    private FormStrategy strategy;
    Validator validator = new Validator();
    Scanner scan = new Scanner(System.in);

    public UserForm(FormStrategy strategy) {
        this.strategy = strategy;
    }

    public void showForm() {
        System.out.println("####################################");
        System.out.println("\n=== FORMULÁRIO DE CADASTRO ===");
        System.out.println("Digite seu nome: ");
        String name = validator.validateName(scan.nextLine());
        System.out.println("Digite seu email: ");
        String email = validator.validateEmail(scan.nextLine());
        System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
        LocalDate date = validator.validateDate(scan.nextLine());
        System.out.println("Digite sua senha: ");
        String password = validator.validatePassword(scan.nextLine());

        User user = new User(name, email, date, UserRole.CONSUMER, EntityStatus.ACTIVE);

        if (strategy.isRoleEditable()) {
            System.out.println("Digite o nível (ADMIN, STOKIST):");
            String role = validator.validateRole(scan.nextLine().toUpperCase()); 
            user.setUserRole(UserRole.valueOf(role));
        }
        strategy.setUserData(user, password);

        System.out.println("Processamento concluído!");
    }

}
