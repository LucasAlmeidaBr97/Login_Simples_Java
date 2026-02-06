package service.strategy;

import model.User;
import service.UserService;

public class SelfRegistrationStrategy implements UserRegistrationStrategy {
    private final UserService userService = new UserService();

    @Override
    public void register(User user, String password) {
        userService.registerNewUser(user, password);
        System.out.println("Log: Enviando e-mail de boas-vindas para " + user.getEmail());
    }

}
