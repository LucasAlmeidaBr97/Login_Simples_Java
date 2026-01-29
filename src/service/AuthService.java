package service;

import java.util.List;

import model.Credentials;

import model.enums.EntityStatus;
import repository.CredentialsDAO;
import repository.UserDAO;

public class AuthService {

    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();

    public void login(String email, String password) {
        if (existUser(email)) {
            Long userId = userDAO.findByEmail(email).getId();
            if (validatePassword(userId, password)) {
                System.out.println("Login Efetuado com sucesso!");
            } else {
                System.out.println("Senha incorreta!");
            }

        } else {
            System.out.println("Email não cadastrado");
        }

    }

    public boolean existUser(String email) {
        System.out.println(userDAO.findByEmail(email));
        return userDAO.findByEmail(email) != null;
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