package service.strategy;

import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;
import service.UserService;

public class RegistrationStrategy implements UserRegistrationStrategy {
    private final UserService userService = new UserService();

    @Override
    public void register(User user, String password) {
        user.setUserRole(UserRole.STOKIST);
        user.setStatus(EntityStatus.ACTIVE);
        userService.registerNewUser(user, password);
        System.out.println("Log: Enviando e-mail de boas-vindas para " + user.getEmail());
    }

}
