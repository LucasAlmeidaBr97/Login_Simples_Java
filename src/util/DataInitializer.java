package util;

import java.time.LocalDate;

import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;
import repository.CredentialsDAO;
import repository.UserDAO;

public class DataInitializer {

    public static void init() {
        UserDAO userDAO = UserDAO.getInstance();
        CredentialsDAO credentialsDAO = CredentialsDAO.getInstance();
        initUsers(userDAO);
        initCredentials(credentialsDAO, userDAO);
    }

    private static void initUsers(UserDAO userDAO) {
        userDAO.saveUser(new User("João", "joao@email.com",
                LocalDate.of(2002, 2, 2), UserRole.CONSUMER, EntityStatus.ACTIVE));

        userDAO.saveUser(new User("Carlos", "carlos@email.com",
                LocalDate.of(2002, 2, 2), UserRole.CONSUMER, EntityStatus.ACTIVE));

        userDAO.saveUser(new User("Roberto", "roberto@email.com",
                LocalDate.of(2002, 2, 2), UserRole.ADMIN, EntityStatus.ACTIVE));

        userDAO.saveUser(new User("Lucas", "lucas@email.com",
                LocalDate.of(2002, 2, 2), UserRole.STOKIST, EntityStatus.ACTIVE));
    }

    private static void initCredentials(CredentialsDAO credentialsDAO, UserDAO userDAO) {

        credentialsDAO.addCredential(userDAO.findByEmail("joao@email.com"), "Joao.2000");
        credentialsDAO.addCredential(userDAO.findByEmail("joao@email.com"), "Joao.2001");

        credentialsDAO.addCredential(userDAO.findByEmail("carlos@email.com"), "Carlos.2000");
        credentialsDAO.addCredential(userDAO.findByEmail("roberto@email.com"), "Roberto.2000");
        credentialsDAO.addCredential(userDAO.findByEmail("lucas@email.com"), "Lucas.2000");
    }

}
