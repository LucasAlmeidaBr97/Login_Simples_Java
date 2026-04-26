package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import auth.AuthService;
import model.User;
import model.enums.EntityStatus;
import service.strategy.AdminServiceStrategy;
import ui.UpdateForms.UpdateResult;

public class AdminMenu implements Menu {
    private final SearchForms searchForms = new SearchForms();
    private final List<User> userFinder = new ArrayList<>();
    private final MenuNavigator navigator = new MenuNavigator();
    private final AuthService authService = new AuthService();

    private final AdminServiceStrategy strategy = new AdminServiceStrategy();
    private final UpdateForms updateForms = new UpdateForms(strategy, strategy);
    private UserUpdateFlow userUpdateFlow = new UserUpdateFlow(navigator, updateForms);

    private User selectedUser;

    @Override
    public void showMenu() {
        System.out.println("\n####################################");
        System.out.println("            Painel do ADMIN");
        System.out.println("1. Buscar Usuário");
        System.out.println("2. Cadastrar Novo Usuário");
        System.out.println("0. Logout");
        System.out.print("Escolha: ");
    }

    public void findMenu() {
        System.out.println("\n####################################");
        System.out.println("            Escolha uma opção");
        System.out.println("1. Interno (STOKIST/ADMIN)");
        System.out.println("2. Externo (CONSUMER)");
        System.out.println("0. Voltar.");
    }

    public void findInternalUser() {
        System.out.println("\n####################################");
        System.out.println("            Buscar por Usuários Internos");
        System.out.println("1. Buscar por Nome");
        System.out.println("2. Buscar por E-mail");
        System.out.println("3. Buscar por Status");
        System.out.println("4. Buscar por Papel");
        System.out.println("0. Voltar.");
    }

    public void findExternalUser() {
        System.out.println("\n####################################");
        System.out.println("            Buscar por Usuários Externos");
        System.out.println("1. Buscar por Nome");
        System.out.println("2. Buscar por E-mail");
        System.out.println("3. Buscar por Status");
        System.out.println("0. Voltar.");
    }

    public void findByStatusMenu() {
        System.out.println("--------------------------------------");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Ativo | 2. Inativo | 3. Bloqueado | 0. Voltar");

    }

    public void showUsers() {
        System.out.println("\n####################################");
        System.out.println("            Usuários encontrados");
        for (User user : userFinder) {
            System.out.println("--------------------------------------");
            System.out.println(user);
            System.out.println("--------------------------------------");
        }
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Selecionar usuário");
        System.out.println("0. Voltar");
    }

    public void selecetedUserProfile() {
        System.out.println("\n####################################");
        System.out.println("      Usuário selecionado");

        System.out.println("--------------------------------------");
        System.out.println("Nome: " + selectedUser.getName() + " | Nascimento: " + selectedUser.getBirthDate());
        System.out.println("E-mail: " + selectedUser.getEmail() + " | Status: " + selectedUser.getStatus());
        System.out.println("--------------------------------------");
    }

    public void profileOptions() {
        System.out.println("--------------------------------------");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Editar dados pessoais");
        System.out.println("2. " + optionStatusLabel());
        System.out.println("3. Resetar Senha de Usuário");
        System.out.println("0. Voltar");
    }

    public void updateStatusMenu() {
        EntityStatus status = selectedUser.getStatus();
        System.out.println("--------------------------------------");
        System.out.println("O status atual da conta é: " + status.getLabel());
        if (status == EntityStatus.ACTIVE) {
            System.out.println("1. Desativar | 2. Bloquear | 0. Voltar");
        } else if (status == EntityStatus.INACTIVE) {
            System.out.println("1. Ativar | 0. Voltar");
        } else if (status == EntityStatus.LOCKED) {
            System.out.println("1. Desbloquear | 0. Voltar");
        }
    }

    public String optionStatusLabel() {
        EntityStatus current = selectedUser.getStatus();
        switch (current) {
            case ACTIVE:
                return "Desativar ou Bloquear";

            case INACTIVE:
                return "Ativar ou Bloquear";

            case LOCKED:
                return "Desbloquear";

            default:
                return "Status desconhecido";
        }
    }

    private void findInternal(Supplier<List<User>> searchStrategy) {
        List<User> users = searchStrategy.get();

        if (users.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            userFinder.clear();
            userFinder.addAll(users);
            userResultFlow();
        }
    }

    private void selectUser() {
        User user = searchForms.selectById();

        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (userFinder.stream().noneMatch(u -> u.getId().equals(user.getId()))) {
            System.out.println("Selecione um usuário da lista exibida.");
            return;
        }
        selectedUser = user;
        selectedUserFlow();
    }

    public void updateStatus() {
        var result = updateForms.updateStatus(1, selectedUser);
        System.out.println("aqui");
    }

    @Override
    public void setPath() {
        navigator.navigate(
                this::showMenu,
                Map.of(
                        1, this::findMenuFlow,
                        0, () -> {
                            System.out.println("Desconectando ... ");
                            authService.logout();
                            navigator.stop();
                        }));
    }

    public void findMenuFlow() {
        navigator.navigate(
                this::findInternalUser,
                Map.of(
                        1, () -> findInternal(searchForms::searchByNameForm),
                        2, () -> findInternal(searchForms::searchByEmailForm),
                        3, this::findByStatusFlow,
                        0, () -> {
                        }));
    }

    public void findByStatusFlow() {
        navigator.navigate(
                this::findByStatusMenu,
                Map.of(
                        1, () -> findInternal(() -> searchForms.searchByStatusForm(EntityStatus.ACTIVE)),
                        2, () -> findInternal(() -> searchForms.searchByStatusForm(EntityStatus.INACTIVE)),
                        3, () -> findInternal(() -> searchForms.searchByStatusForm(EntityStatus.LOCKED)),
                        0, () -> {
                        }));
    }

    public void userResultFlow() {
        navigator.navigate(
                this::showUsers,
                Map.of(
                        1, this::selectUser,
                        0, () -> {
                        }));
    }

    public void selectedUserFlow() {
        selecetedUserProfile();
        navigator.navigate(
                this::profileOptions,
                Map.of(
                        1, userUpdateFlow::updatePersonalFlow,
                        2, this::updateStatusFlow,
                        0, () -> {
                        }));
    }

    public void updateStatusFlow() {
        navigator.navigate(
                this::updateStatusMenu,
                Map.of(
                        1, this::updateStatus,
                        0, () -> {

                        }));
    }

}
