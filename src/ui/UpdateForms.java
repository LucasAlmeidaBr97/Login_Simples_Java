package ui;

import java.time.LocalDate;
import java.util.Scanner;

import auth.AuthService;
import auth.UserSession;
import model.User;
import service.UserService;
import service.strategy.FormStrategy;
import util.Validator;

public class UpdateForms {
    private FormStrategy strategy;
    Scanner scan = new Scanner(System.in);
    Validator validator = new Validator();
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();

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
            strategy.setUserData(user, password);
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
            strategy.setUserData(user, password);
        } else {
            System.out.println("\n ### Senha incorreta não foi possível atualizar seu aniversário. ###");
        }
    }

    public void passwordForm() {

    }
}
