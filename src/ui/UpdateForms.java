package ui;

import java.time.LocalDate;
import java.util.Scanner;

import auth.AuthService;
import auth.UserSession;
import model.User;
import model.enums.EntityStatus;
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
    private final MenuNavigator navigator = new MenuNavigator();

    public enum UpdateResult {
        UPDATED,
        LOGOUT,
        CANCEL
    }

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
            System.out.println("\n============================");
            System.out.println("Nome atualizado com sucesso!");
            System.out.println("============================");
            strategy.setUserData(user, null);
        } else {
            System.out.println("\n-------------------------------------------------------------");
            System.out.println(" ### Senha incorreta não foi possível atualizar seu nome. ###");
            System.out.println("-------------------------------------------------------------");
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
            System.out.println("\n===================================");
            System.out.println("Aniversário atualizado com sucesso!");
            System.out.println("===================================");
            strategy.setUserData(user, null);
        } else {
            System.out.println("\n--------------------------------------------------------------------");
            System.out.println(" ### Senha incorreta não foi possível atualizar seu aniversário. ###");
            System.out.println("--------------------------------------------------------------------");
        }
    }

    public void passwordForm() {
        System.out.println("Digite sua senha atual: ");
        String password = validator.validatePassword(scan.nextLine());
        if (authService.validatePassword(UserSession.getInstance().getUserId(), password)) {
            System.out.println("Digite sua nova senha: ");
            String newPassword = validator.validatePassword(scan.nextLine());
            System.out.println("Confirme sua nova senha: ");
            String newPassword2 = validator.validatePassword(scan.nextLine());
            if (newPassword.equals(newPassword2)) {
                System.out.println("\n=============================");
                System.out.println("Senha atualizada com sucesso!");
                System.out.println("=============================");
                strategy.setUserData(userService.getUser(UserSession.getInstance().getEmail()), newPassword);
            } else {
                System.out.println("\n--------------------------------------------------------------------------");
                System.out.println(" ### Não foi possível Atualizar sua senha. Senhas informadas divergem  ###");
                System.out.println("--------------------------------------------------------------------------");
            }
        } else {
            System.out.println("\n-------------------------------------------------------------");
            System.out.println(" ### Senha incorreta não foi possível dar continuidade. ###");
            System.out.println("-------------------------------------------------------------");
        }
    }

    public UpdateResult updateStatus() {
        System.out.println("Digite sua senha para confirmar as atualizações: ");
        String password = validator.validatePassword(scan.nextLine());

        if (!authService.validatePassword(
                UserSession.getInstance().getUserId(), password)) {
            return UpdateResult.CANCEL;
        }

        User user = userService.getUser(
                UserSession.getInstance().getEmail());

        if (user.getStatus() == EntityStatus.ACTIVE) {
            user.setStatus(EntityStatus.INACTIVE);
            strategy.setUserData(user, null);
            System.out.println("Usuário desativado. Saindo...");
            return UpdateResult.LOGOUT;
        }

        user.setStatus(EntityStatus.ACTIVE);
        strategy.setUserData(user, null);
        return UpdateResult.UPDATED;
    }

    public void updateUser(User user) {
        System.out.println("Digite seu nome: ");
        String name = validator.validateName(scan.nextLine());
        System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
        LocalDate date = validator.validateDate(scan.nextLine());
        user.setName(name);
        user.setBirthDate(date);
        passwordForm();
        strategy.setUserData(user, null);
    }
}
