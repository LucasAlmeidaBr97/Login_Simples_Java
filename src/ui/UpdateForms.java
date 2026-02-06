package ui;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Consumer;

import auth.AuthService;
import auth.UserSession;
import model.User;
import model.enums.EntityStatus;
import service.UserService;
import service.strategy.CredentialsUpdateStrategy;
import service.strategy.UserUpdateStrategy;
import util.Validator;

public class UpdateForms {
    private final UserUpdateStrategy userStrategy;
    private final CredentialsUpdateStrategy credStrategy;
    private final Scanner scan = new Scanner(System.in);
    private final Validator validator = new Validator();
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();

    public enum UpdateResult {
        UPDATED,
        LOGOUT,
        CANCEL
    }

    public UpdateForms(UserUpdateStrategy userStrategy, CredentialsUpdateStrategy credStrategy) {
        this.userStrategy = userStrategy;
        this.credStrategy = credStrategy;
    }

    private boolean executeSecureUpdate(String successMsg, Consumer<User> updateLogic) {
        System.out.println("\nDigite sua senha para confirmar as atualizações: ");
        String password = validator.validatePassword(scan.nextLine());

        if (authService.validatePassword(UserSession.getInstance().getUserId(), password)) {
            User user = userService.getUser(UserSession.getInstance().getEmail());

            updateLogic.accept(user);
            userStrategy.update(user); 

            System.out.println("\n============================");
            System.out.println(successMsg + " com sucesso!");
            System.out.println("============================");
            return true;
        } else {
            System.out.println("\n[ERRO] Senha incorreta. Operação cancelada.");
            return false;
        }
    }

    public void nameForm() {
        System.out.println("Digite seu novo nome: ");
        String name = validator.validateName(scan.nextLine());
        executeSecureUpdate("Nome atualizado", user -> user.setName(name));
    }

    public void birthForm() {
        System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
        LocalDate date = validator.validateDate(scan.nextLine());
        executeSecureUpdate("Aniversário atualizado", user -> user.setBirthDate(date));
    }

    public void passwordForm() {
        System.out.println("Digite sua senha ATUAL: ");
        String currentPassword = validator.validatePassword(scan.nextLine());

        if (authService.validatePassword(UserSession.getInstance().getUserId(), currentPassword)) {
            System.out.println("Digite sua NOVA senha: ");
            String newPwd = validator.validatePassword(scan.nextLine());
            System.out.println("Confirme a nova senha: ");
            String confirmPwd = validator.validatePassword(scan.nextLine());

            if (newPwd.equals(confirmPwd)) {
                User user = userService.getUser(UserSession.getInstance().getEmail());
                credStrategy.updatePassword(user, newPwd); 
                System.out.println("Senha atualizada com sucesso!");
            } else {
                System.out.println("As senhas informadas divergem.");
            }
        } else {
            System.out.println("Senha incorreta.");
        }
    }

    public UpdateResult updateStatus() {
        User user = userService.getUser(UserSession.getInstance().getEmail());
        EntityStatus newStatus = (user.getStatus() == EntityStatus.ACTIVE) ? EntityStatus.INACTIVE
                : EntityStatus.ACTIVE;
        String label = (newStatus == EntityStatus.INACTIVE) ? "desativado" : "ativado";

        boolean success = executeSecureUpdate("Status " + label, u -> u.setStatus(newStatus));

        if (success && newStatus == EntityStatus.INACTIVE) {
            return UpdateResult.LOGOUT;
        }
        return success ? UpdateResult.UPDATED : UpdateResult.CANCEL;
    }
}
