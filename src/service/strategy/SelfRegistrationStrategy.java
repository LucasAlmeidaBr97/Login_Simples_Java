package service.strategy;

import model.User;
import service.UserService;

public class SelfRegistrationStrategy implements FormStrategy {

    private final UserService userService = new UserService();

    @Override
    public void setUserData(User user, String password) {
        userService.registerNewUser(user, password);
    }

    @Override
    public boolean isRoleEditable() {
        return false;
    }

    public void user(){

    }
    
}
