package service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.User;
import model.enums.EntityStatus;
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

    public List<User> getUserByName(String name) {
        List<User> results = new ArrayList<>();
        Map<Long, User> allUsers = userDAO.getUserMap();

        String normalizedInput = normalize(name);

        for (User user : allUsers.values()) {

            String normalizedUserName = normalize(user.getName());

            if (normalizedUserName.contains(normalizedInput)) {
                results.add(user);
            }
        }
        return results;
    }

    public List<User> getUsersByEmail(String email) {
        List<User> results = new ArrayList<>();
        Map<Long, User> allUsers = userDAO.getUserMap();
        for (User user : allUsers.values()) {
            if (user.getEmail().toLowerCase().contains(email.toLowerCase())) {
                results.add(user);
            }
        }
        return results;
    }

    public List<User> getUsersByStatus(EntityStatus status) {
        List<User> results = new ArrayList<>();
        Map<Long, User> allUsers = userDAO.getUserMap();
        for (User user : allUsers.values()) {
            if (user.getStatus() == status) {
                results.add(user);
            }
        }
        return results;
    }

    public User getUserById(Long id) {
        User UserResult = userDAO.findById(id);
        return UserResult;
    }

    public static String normalize(String str) {
        return Normalizer
                .normalize(str, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

}
