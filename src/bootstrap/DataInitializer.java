package bootstrap;

import repository.CredentialsDAO;
import repository.UserDAO;

public class DataInitializer {
    public static void init() {
        
        UserDAO userDAO = UserDAO.getInstance();
        CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();
        userDAO.initUsers();
        credentialsDAO.initCredentials();
    }
}
