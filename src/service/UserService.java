package service;

import model.User;
import repository.UserDAO;

public class UserService {

    private static final UserDAO userDAO = UserDAO.getInstance();

    public void registerNewUser(User newUser) {
        if (existUser(newUser.getEmail())) {
            System.out.println("Email já cadastrado");
            return;
        }
        userDAO.saveUser(newUser);
        
    }

    public boolean existUser(String email) {
        return userDAO.findByEmail(email) != null;
    }

}
