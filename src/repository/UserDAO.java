package repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;

public class UserDAO {

    private static final UserDAO instance = new UserDAO();

    private final Map<Long, User> users = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();

    private UserDAO() {
        
    }

    public static UserDAO getInstance() {
        return instance;
    }

    public void initUsers() {
        saveUser(new User("João", "joao@email.com",
                LocalDate.of(2002, 2, 2), UserRole.CONSUMER, EntityStatus.ACTIVE));

        saveUser(new User("Carlos", "carlos@email.com",
                LocalDate.of(2002, 2, 2), UserRole.CONSUMER, EntityStatus.ACTIVE));

        saveUser(new User("Roberto", "roberto@email.com",
                LocalDate.of(2002, 2, 2), UserRole.ADMIN, EntityStatus.ACTIVE));

        saveUser(new User("Lucas", "lucas@email.com",
                LocalDate.of(2002, 2, 2), UserRole.STOKIST, EntityStatus.ACTIVE));
    }

    

    public User findByEmail(String email) {
        return usersByEmail.get(email);
    }

    public Long generateNextUserId() {
        return users.keySet()
                .stream()
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
    
    public void saveUser(User user) {
        user.setId(generateNextUserId());
        users.put(user.getId(), user);
        usersByEmail.put(user.getEmail(), user);
    }

}
