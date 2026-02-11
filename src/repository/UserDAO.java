package repository;

import java.util.HashMap;
import java.util.Map;

import model.User;

public class UserDAO {

    private static final UserDAO instance = new UserDAO();

    private final Map<Long, User> users = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return instance;
    }

    public User findByEmail(String email) {
        return usersByEmail.get(email.toLowerCase());
    }

    public Long generateNextUserId() {
        return users.keySet()
                .stream()
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }

    public Map<Long, User> getUserMap() {
        return users;
    }

    public void saveUser(User user) {
        user.setId(generateNextUserId());
        users.put(user.getId(), user);
        usersByEmail.put(user.getEmail().toLowerCase(), user);
    }

    public void updateUser(User user){
        users.put(user.getId(), user);
        usersByEmail.put(user.getEmail().toLowerCase(), user);
    }

}
