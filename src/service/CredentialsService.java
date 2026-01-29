package service;

import java.util.List;
import java.util.Map;

import model.Credentials;
import model.User;
import repository.CredentialsDAO;
import repository.UserDAO;

public class CredentialsService {
    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CredentialsDAO credentialsDAO = CredentialsDAO.getInstance(); 
    
    public void registerNewCredentials(User newUser, String password) {
        newUser.setId(userDAO.findByEmail(newUser.getEmail()).getId());
        credentialsDAO.addCredential(newUser, password);
    }

    public void listCredentials() {
        Map<Long, List<Credentials>> allCredentials = credentialsDAO.getCredentialsMap();
        for (List<Credentials> credentialList : allCredentials.values()) {
            for (Credentials credentials : credentialList) {
                System.out.println(credentials);
            }
        }
    }

}
