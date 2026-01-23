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

    public void saveUser(User user) {
        users.put(user.getId(), user);
    }

   

}
