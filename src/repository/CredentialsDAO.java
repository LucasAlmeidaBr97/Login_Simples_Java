package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Credentials;
import model.User;

public class CredentialsDAO {

    private static final CredentialsDAO instance = new CredentialsDAO();
    private final Map<Long, List<Credentials>> credentialsByUserId = new HashMap<>();

    private CredentialsDAO() {
        initCredentials();
    }

    public static CredentialsDAO getInstance() {
        return instance;
    }

    private void initCredentials() {

        UserDAO userDAO = UserDAO.getInstance();

        addCredential(userDAO.findByEmail("joao@email.com"), "Joao.2000");
        addCredential(userDAO.findByEmail("joao@email.com"), "Joao.2001");

        addCredential(userDAO.findByEmail("carlos@email.com"), "Carlos.2000");
        addCredential(userDAO.findByEmail("roberto@email.com"), "Roberto.2000");
        addCredential(userDAO.findByEmail("lucas@email.com"), "Lucas.2000");

    }

    public void addCredential(User user, String password) {
        Credentials credentials = new Credentials(
                generateNextCredentialId(),
                user.getId(),
                password);

        credentialsByUserId
                .computeIfAbsent(user.getId(), k -> new ArrayList<>())
                .add(credentials);

    }

    private Long generateNextCredentialId() {
        return credentialsByUserId.values().stream()
                .flatMap(List::stream)
                .map(Credentials::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }

}
