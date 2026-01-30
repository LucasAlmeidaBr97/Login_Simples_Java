package auth;

import java.util.List;

import model.Credentials;
import model.User;
import model.enums.EntityStatus;
import repository.CredentialsDAO;
import repository.UserDAO;

public class AuthService {

    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();

    public boolean login(String email, String password) {

        User user = userDAO.findByEmail(email);

        if (user == null) {
            System.out.println("Email não cadastrado");
        }

        if (user.getStatus() != EntityStatus.ACTIVE) {
            System.out.println("Usuário está inativo ou bloqueado.");
            return false;
        }

        if (validatePassword(user.getId(), password)) {
            UserSession.getInstance().startSession(user.getId(), user.getEmail(), user.getUserRole());
            System.out.println("Login Efetuado com sucesso!");
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

}