package ui;

import java.util.Map;

import auth.UserSession;
import model.User;
import model.enums.EntityStatus;
import service.UserService;

class UserUpdateFlow {
    private final MenuNavigator navigator;
    private final UpdateForms updateForms;
    private final UserService userService = new UserService();

    public UserUpdateFlow(MenuNavigator navigator, UpdateForms updateForms) {
        this.navigator = navigator;
        this.updateForms = updateForms;
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

    public void updateStatusMenu(User targetUser) {
        EntityStatus status = targetUser.getStatus();
        System.out.println("--------------------------------------");
        System.out.println("O status atual da sua conta é: " + status.getLabel());
        if (status == EntityStatus.ACTIVE) {
            System.out.println("1. Desativar | 0. Voltar |");
        } else if (status == EntityStatus.INACTIVE) {
            System.out.println("1. Ativar | 0. Voltar |");
        }
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

    public void updatePersonalFlow() {
        navigator.navigate(
                this::updatePersonalOptions,
                Map.of(
                        1, updateForms::nameForm,
                        2, updateForms::birthForm,
                        0, () -> {
                        }));
    }

    public void updateAccountFlow() {
        navigator.navigate(
                this::updateAccountOptions,
                Map.of(
                        1, updateForms::passwordForm,
                        2, this::updateStatusFlow,
                        0, () -> {
                        }));
    }

    private void updateStatusFlow() {
        navigator.navigate(
                this::updateStatusMenu,
                Map.of(
                        1, updateForms::updateStatus,
                        0, () -> {
                        }));
    }


}
