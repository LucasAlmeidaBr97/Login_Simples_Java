package ui;

import java.util.Map;

import auth.AuthService;
import auth.UserSession;
import model.User;
import model.enums.EntityStatus;
import service.UserService;
import service.strategy.SelfServiceStrategy;
import ui.UpdateForms.UpdateResult;

public class ConsumerMenu implements Menu {

    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();
    private final MenuNavigator navigator = new MenuNavigator();

    private final SelfServiceStrategy strategy = new SelfServiceStrategy();
    private final UpdateForms updateForms = new UpdateForms(strategy, strategy);

    @Override
    public void showMenu() {
        System.out.println("\n####################################");
        System.out.println("            Seja Bem Vindo!");
        System.out.println("Escolha uma opção");
        System.out.println("1. Meu Perfil | 2. Log-out |");
    }

    public void consumerProfile() {
        System.out.println("\n####################################");
        System.out.println("      Informações do seu perfil");
        String email = UserSession.getInstance().getEmail();
        User user = userService.getUser(email);

        System.out.println("--------------------------------------");
        System.out.println("Nome: " + user.getName() + " | Nascimento: " + user.getBirthDate());
        System.out.println("E-mail: " + user.getEmail() + " | Status: " + user.getStatus());
        System.out.println("--------------------------------------");

    }

    public void profileOptions() {
        System.out.println("--------------------------------------");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Editar dados pessoais");
        System.out.println("2. Editar dados da conta");
        System.out.println("0. voltar");
    }

    public void updateAccountOptions() {
        System.out.println("\n########################################");
        System.out.println("      Atualizar informações da sua conta");
        System.out.println("Escolha uma opção");
        System.out.println("1. Senha | 2. Ativar/Desativar | 0. Voltar");
    }

    public void updatePersonalOptions() {
        System.out.println("\n#########################################");
        System.out.println("      Atualizar informações do seu perfil");
        System.out.println("Escolha uma opção");
        System.out.println("1. Nome | 2. Data de nascimento | 0. Voltar");
    }

    public void updateStatusMenu() {
        EntityStatus status = userService.getUser(UserSession.getInstance().getEmail()).getStatus();
        System.out.println("--------------------------------------");
        System.out.println("O status atual da sua conta é: " + status.getLabel());
        if (status == EntityStatus.ACTIVE) {
            System.out.println("1. Desativar | 0. Voltar |");
        } else if (status == EntityStatus.INACTIVE) {
            System.out.println("1. Ativar | 0. Voltar |");
        }
    }

    public void updateStatus() {
        
        var result = updateForms.updateStatus();
        if (result == UpdateResult.LOGOUT) {
            authService.logout();
            navigator.stop();
        }
    }

    public void updatePassword() {
        updateForms.passwordForm();
    }

    public void updateName() {
        updateForms.nameForm();
    }

    public void updateBirth() {
        updateForms.birthForm();
    }

    @Override
    public void setPath() {
        navigator.navigate(
                this::showMenu, // <- Refêrencia ao método show menu
                Map.of(// <- Cria map ja preenchido
                        1, this::profileFlow, // <-- ação 1
                        2, () -> { // () -> { ... } () = nenhum parametro, {} corpo do bloco run() Runnable na
                                   // "mão"
                            authService.logout();
                            navigator.stop();
                        }

                ));
    }

    public void updateStatusFlow() {
        navigator.navigate(this::updateStatusMenu,
                Map.of(
                        1, this::updateStatus,
                        0, () -> {

                        }));
    }

    public void profileFlow() {
        consumerProfile();
        navigator.navigate(
                this::profileOptions,
                Map.of(
                        1, this::updatePersonalFlow,
                        2, this::updateAccountFlow,
                        0, () -> {
                        }));
    }

    public void updatePersonalFlow() {
        navigator.navigate(
                this::updatePersonalOptions,
                Map.of(1, this::updateName,
                        2, this::updateBirth,
                        0, () -> {
                        }));
    }

    public void updateAccountFlow() {
        navigator.navigate(
                this::updateAccountOptions,
                Map.of(1, this::updatePassword,
                        2, this::updateStatusFlow,
                        0, () -> {
                        }));
    }
}
