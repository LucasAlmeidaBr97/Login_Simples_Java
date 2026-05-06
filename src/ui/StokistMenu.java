package ui;

import java.util.Map;

import auth.AuthService;
import auth.UserSession;
import model.User;
import service.strategy.SelfUpdadeStrategy;

public class StokistMenu extends BaseUserMenu {

    private final AuthService authService = new AuthService();
    private final SelfUpdadeStrategy strategy = new SelfUpdadeStrategy();
    private final UpdateForms updateForms = new UpdateForms(strategy, strategy);

    @Override
    protected void printOptions() {
        System.out.println("1. Meu Perfil | 2. Alterar Senha | 0. Logout");
    }

    @Override
    protected Map<Integer, Runnable> getActions() {
        return Map.of(
                1, this::showProfile,
                2, this::updatePassword,
                0, this::logout);

    }

    private void logout() {
        System.out.println("Desconectando ... ");
        authService.logout();
        navigator.stop();
    }

    public void updatePassword() {
        String email = UserSession.getInstance().getEmail();
        User user = userService.getUser(email);
        updateForms.passwordForm(user);
    }

}
