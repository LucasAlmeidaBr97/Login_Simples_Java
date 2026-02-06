package auth;

import java.util.List;
import java.util.Scanner;

import model.Credentials;
import model.User;
import model.enums.EntityStatus;
import repository.CredentialsDAO;
import repository.UserDAO;
import service.strategy.UserSaveStrategy;
import service.strategy.SelfUpdadeStrategy;
import ui.UpdateForms;

public class AuthService {

    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();
    private static Scanner scan = new Scanner(System.in);

    public boolean login(String email, String password) {

        User user = userDAO.findByEmail(email);

        if (user == null) {
            System.out.println("Email não cadastrado");
        }

        if (user.getStatus() == EntityStatus.INACTIVE) {
            System.out.println("Usuário desativado.");
            System.out.println("Deseja reativar a conta?");
            System.out.println("1. Sim | 0. Voltar");

            int option = scan.nextInt();
            scan.nextLine();

            if (option == 1) {
                UserSaveStrategy strategy = new SelfUpdadeStrategy();
                UpdateForms forms = new UpdateForms(strategy);
                forms.reactivateUser(user); 
            }
            return false;
        }

        if (user.getStatus() == EntityStatus.LOCKED) {
            System.out.println("Usuário está bloqueado contate um administrador.");
            return false;
        }

        if (validatePassword(user.getId(), password)) {
            UserSession.getInstance().startSession(user.getId(), user.getEmail(), user.getUserRole());
            System.out.println("\n===========================");
            System.out.println("Login Efetuado com sucesso!");
            System.out.println("===========================");
            return true;
        } else {
            System.out.println("Senha incorreta!");
            return false;
        }

    }

    public boolean validatePassword(Long userId, String password) {
        List<Credentials> list = credentialsDAO.findByUserId(userId);
        for (Credentials c : list) {
            if (c.getStatus() == EntityStatus.ACTIVE) {
                if (password.equals(c.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getPassword(Long userId) {
        List<Credentials> list = credentialsDAO.findByUserId(userId);

        if (list == null || list.isEmpty()) {
            return null;
        }

        for (Credentials c : list) {
            if (c.getStatus() == EntityStatus.ACTIVE) {
                return c.getPassword();
            }
        }

        return null;
    }

    public void logout() {
        UserSession.getInstance().stopSession();

    }

}