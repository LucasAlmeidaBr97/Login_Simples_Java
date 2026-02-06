package service.strategy;

import model.User;
import model.enums.EntityStatus;
import service.UserService;

public class ReactivationStrategy implements UserRegistrationStrategy {
    private final UserService userService = new UserService();

    @Override
    public void register(User user, String password){
        user.setStatus(EntityStatus.ACTIVE);
        userService.processReactivation(user, password);
    }
}
