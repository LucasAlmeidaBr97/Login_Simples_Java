package auth;

import java.util.List;
import model.Credentials;
import model.User;
import model.enums.EntityStatus;
import repository.CredentialsDAO;
import repository.UserDAO;

public class AuthService {

    private final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();
    
    public enum LoginStatus {
        SUCCESS, USER_NOT_FOUND, INACTIVE, LOCKED, INVALID_PASSWORD
    }

    public LoginStatus authenticate(String email, String password) {
        User user = userDAO.findByEmail(email.toLowerCase());

        if (user == null) {
            return LoginStatus.USER_NOT_FOUND;
        }

        if (user.getStatus() == EntityStatus.INACTIVE) {
            return LoginStatus.INACTIVE;
        }

        if (user.getStatus() == EntityStatus.LOCKED) {
            return LoginStatus.LOCKED;
        }

        if (validatePassword(user.getId(), password)) {
            UserSession.getInstance().startSession(user.getId(), user.getEmail().toLowerCase(), user.getUserRole());
            return LoginStatus.SUCCESS;
        }

        return LoginStatus.INVALID_PASSWORD;

    }

    public boolean validatePassword(Long userId, String password) {
        List<Credentials> list = credentialsDAO.findByUserId(userId);
        return list.stream()
            .filter(c -> c.getStatus() == EntityStatus.ACTIVE)
            .anyMatch(c -> c.getPassword().equals(password));
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