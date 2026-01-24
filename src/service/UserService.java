package service;

import java.util.Map;

import model.User;
import repository.UserDAO;

public class UserService {

    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final AuthService authService = new AuthService();

    public void registerNewUser(User newUser, String password) {
        if (existUser(newUser.getEmail())) {
            System.out.println("Email já cadastrado");
            return;
        }
        userDAO.saveUser(newUser);
        authService.registerNewCredentials(newUser, password);

    }

    public boolean existUser(String email) {
        return userDAO.findByEmail(email) != null;
    }

}
