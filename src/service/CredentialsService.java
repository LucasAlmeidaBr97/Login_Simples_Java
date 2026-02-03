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
    
    public void registerNewCredentials(User user, String password) {
        user.setId(userDAO.findByEmail(user.getEmail()).getId());
        credentialsDAO.addCredential(user, password);
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
