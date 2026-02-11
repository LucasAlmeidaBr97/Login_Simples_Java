package service;

import java.util.Map;

import model.User;
import repository.UserDAO;

public class UserService {

    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsService credentialsService = new CredentialsService();

    public void registerNewUser(User newUser, String password) {
        if (existUser(newUser.getEmail().toLowerCase())) {
            System.out.println("Email já cadastrado");
            return;
        }
        userDAO.saveUser(newUser);
        credentialsService.registerNewCredentials(newUser, password);
    }

    public boolean existUser(String email) {
        return userDAO.findByEmail(email.toLowerCase()) != null;
    }

    public void listUser() {
        Map<Long, User> allUsers = userDAO.getUserMap();
        for (User user : allUsers.values()) {
            System.out.println(user);
        }
    }

    public User getUser(String email) {
        User user = userDAO.findByEmail(email.toLowerCase());
        return user;
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void processReactivation(User user, String password) {
        userDAO.updateUser(user);
        credentialsService.registerNewCredentials(user, password);
        System.out.println("Conta de " + user.getEmail() + " reativada com sucesso!");
    }

}
