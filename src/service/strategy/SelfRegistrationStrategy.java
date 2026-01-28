package service.strategy;

import model.User;
import service.CredentialsService;
import service.UserService;

public class SelfRegistrationStrategy implements FormStrategy {

    private final UserService userService = new UserService();
    private final CredentialsService credentialsService = new CredentialsService();

    @Override
    public void setUserData(User user, String password) {
        userService.registerNewUser(user, password);
        userService.listUser(); 
        credentialsService.listCredentials(); 
    }

    @Override
    public boolean isRoleEditable() {
        return false;
    }

    public void user(){

    }
    
}
