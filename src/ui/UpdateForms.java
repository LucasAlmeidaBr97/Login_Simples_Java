package ui;

import java.time.LocalDate;
import java.util.Scanner;

import auth.AuthService;
import auth.UserSession;
import model.User;
import service.CredentialsService;
import service.UserService;
import service.strategy.FormStrategy;
import util.Validator;

public class UpdateForms {
    private FormStrategy strategy;
    Scanner scan = new Scanner(System.in);
    Validator validator = new Validator();
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();
    private final CredentialsService credentialsService = new CredentialsService();

    public UpdateForms(FormStrategy strategy) {
        this.strategy = strategy;
    }

    public void showForm() {
        System.out.println("\n####################################");
        System.out.println("      Atualizar nome");
        System.out.println("Digite seu nome: ");
        String name = validator.validateName(scan.nextLine());
        System.out.println("Digite sua senha para confirmar as atualizações: ");
        String password = validator.validatePassword(scan.nextLine());
        if (authService.validatePassword(UserSession.getInstance().getUserId(), password)) {
            User user = userService.getUser(UserSession.getInstance().getEmail());
            user.setName(name);
            System.out.println("\n===========================");
            System.out.println("Nome atualizado com sucesso!");
            System.out.println("===========================");
            strategy.setUserData(user, null);
        } else {
            System.out.println("\n ### Senha incorreta não foi possível atualizar seu nome. ###");
        }
    }

    public void birthForm() {
        System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
        LocalDate date = validator.validateDate(scan.nextLine());
        System.out.println("Digite sua senha para confirmar as atualizações: ");
        String password = validator.validatePassword(scan.nextLine());
        if (authService.validatePassword(UserSession.getInstance().getUserId(), password)) {
            User user = userService.getUser(UserSession.getInstance().getEmail());
            user.setBirthDate(date);
            strategy.setUserData(user, null);
        } else {
            System.out.println("\n ### Senha incorreta não foi possível atualizar seu aniversário. ###");
        }
    }

    public void passwordForm() {
        System.out.println("Digite sua senha atual: ");
        String password = validator.validatePassword(scan.nextLine());
        if (authService.validatePassword(UserSession.getInstance().getUserId(), password)){
            System.out.println("Digite sua nova senha: ");
            String newPassword = validator.validatePassword(scan.nextLine());
            System.out.println("Confirme sua nova senha: ");
            String newPassword2 = validator.validatePassword(scan.nextLine());
            if (newPassword.equals(newPassword2)) {
                strategy.setUserData(userService.getUser(UserSession.getInstance().getEmail()), newPassword);   
            }    
        } else {
            System.out.println("\n ### Senha incorreta não foi possível dar continuidade. ###");    
        }
    }
}
