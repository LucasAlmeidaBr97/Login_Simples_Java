package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Credentials;
import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;

public class UserDAO {

    Map<Long, User> users = new HashMap<>();
    Map<Long, List<Credentials>> credentialsByUserId = new HashMap<>();

    private void initUsers() {
        saveUser(new User(1L, "João", "joao@email.com",
                LocalDate.of(2002, 2, 2), UserRole.CONSUMER, EntityStatus.ACTIVE));

        saveUser(new User(2L, "Carlos", "carlos@email.com",
                LocalDate.of(2002, 2, 2), UserRole.CONSUMER, EntityStatus.ACTIVE));

        saveUser(new User(3L, "Roberto", "roberto@email.com",
                LocalDate.of(2002, 2, 2), UserRole.ADMIN, EntityStatus.ACTIVE));

        saveUser(new User(4L, "Lucas", "Lucas@email.com",
                LocalDate.of(2002, 2, 2), UserRole.STOKIST, EntityStatus.ACTIVE));
    }

    private void initCredentials(){
        saveCredential(new Credentials(1L, 1L, "Joao.2000"));
        saveCredential(new Credentials(2l, 2L, "Carlos.2000"));
        saveCredential(new Credentials(3L, 3L, "Roberto.2000"));
        saveCredential(new Credentials(4L, 4L, "Lucas.2000"));
        saveCredential(new Credentials(5L, 1L, "Joao.2001"));
    }



    public void saveUser(User user) {
        users.put(user.getId(), user);
    }

    public void saveCredential(Credentials credential) {
        credentialsByUserId
                .computeIfAbsent(credential.getUserId(), id -> new ArrayList<>())
                .add(credential);
    }

}
