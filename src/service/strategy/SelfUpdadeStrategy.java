package service.strategy;

import model.User;
import service.UserService;

public class SelfUpdadeStrategy implements FormStrategy {

    private final UserService userService = new UserService();

    @Override
    public void setUserData(User user, String password) {
        userService.updateUser(user);
        userService.listUser();
    }

    @Override
    public boolean isRoleEditable() {
        return false;
    }

}