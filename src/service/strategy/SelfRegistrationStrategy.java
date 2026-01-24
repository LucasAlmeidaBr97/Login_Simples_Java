package service.strategy;

import model.User;
import service.AuthService;
import service.UserService;

public class SelfRegistrationStrategy implements FormStrategy {

    private final UserService userService = new UserService();
    private final AuthService authService = new AuthService();

    @Override
    public void setUserData(User user, String password) {
        userService.registerNewUser(user, password);
        userService.listUser(); 
        authService.listCredentials(); 
    }

    @Override
    public boolean isRoleEditable() {
        return false;
    }

    public void user(){

    }
    
}
