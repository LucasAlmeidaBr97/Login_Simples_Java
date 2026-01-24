package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Credentials;
import model.User;
import model.enums.EntityStatus;

public class CredentialsDAO {

    private static final CredentialsDAO instance = new CredentialsDAO();
    private final Map<Long, List<Credentials>> credentialsByUserId = new HashMap<>();

    private CredentialsDAO() {

    }

    public static CredentialsDAO getInstance() {
        return instance;
    }

    public void initCredentials() {

        UserDAO userDAO = UserDAO.getInstance();

        addCredential(userDAO.findByEmail("joao@email.com"), "Joao.2000");
        addCredential(userDAO.findByEmail("joao@email.com"), "Joao.2001");

        addCredential(userDAO.findByEmail("carlos@email.com"), "Carlos.2000");
        addCredential(userDAO.findByEmail("roberto@email.com"), "Roberto.2000");
        addCredential(userDAO.findByEmail("lucas@email.com"), "Lucas.2000");

    }

    public void addCredential(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        List<Credentials> list = credentialsByUserId.get(user.getId());

        if (list == null) {
            list = new ArrayList<>();
            credentialsByUserId.put(user.getId(), list);
        }

        for (Credentials c : list) {
            if (c.getStatus() == EntityStatus.ACTIVE) {
                c.desativate();
                break;
            }
        }

        Credentials credentials = new Credentials(
                generateNextCredentialId(),
                user.getId(),
                password);

        list.add(credentials);

    }

    private Long generateNextCredentialId() {
        return credentialsByUserId.values().stream()
                .flatMap(List::stream)
                .map(Credentials::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }

    public List<Credentials> findByUserId(Long userId) {
        return credentialsByUserId.get(userId);
    }

}
