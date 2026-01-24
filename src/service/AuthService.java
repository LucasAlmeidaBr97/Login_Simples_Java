package service;

import model.User;
import repository.CredentialsDAO;
import repository.UserDAO;

public class AuthService {
    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();

    public void registerNewCredentials(User newUser, String password){
        newUser.setId(userDAO.findByEmail(newUser.getEmail()).getId());
        credentialsDAO.addCredential(newUser, password);
    }
}
