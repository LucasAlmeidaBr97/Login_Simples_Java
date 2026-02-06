package service.strategy;

import model.User;

public interface UserRegistrationStrategy {
    void register(User user, String password);
}
