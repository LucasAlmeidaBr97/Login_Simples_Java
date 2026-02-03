package service.strategy;

import model.User;
import service.CredentialsService;
import service.UserService;

public class SelfUpdadeStrategy implements FormStrategy {

    private final UserService userService = new UserService();
    private final CredentialsService credentialsService = new CredentialsService();

    @Override
    public void setUserData(User user, String password) {
        if (password == null) {
            userService.updateUser(user);
            userService.listUser();    
        } else {
            credentialsService.registerNewCredentials(user, password);
        }
    }

    @Override
    public boolean isRoleEditable() {
        return false;
    }

}