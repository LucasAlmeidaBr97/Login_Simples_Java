package service.strategy;

import model.User;
import service.CredentialsService;
import service.UserService;

public class SelfUpdadeStrategy implements UserUpdateStrategy, CredentialsUpdateStrategy {
    private final UserService userService = new UserService();
    private final CredentialsService credentialsService = new CredentialsService();

    @Override
    public void update(User user) {
        userService.updateUser(user);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        credentialsService.registerNewCredentials(user, newPassword);
    }

}
