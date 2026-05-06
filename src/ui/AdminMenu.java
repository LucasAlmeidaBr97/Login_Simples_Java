package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import auth.AuthService;
import model.User;
import model.enums.EntityStatus;
import model.enums.UserRole;
import service.strategy.RegistrationStrategy;
import service.strategy.SelfRegistrationStrategy;
import service.strategy.SelfUpdadeStrategy;
import service.strategy.UserRegistrationStrategy;

public class AdminMenu implements Menu {
    private final SearchForms searchForms = new SearchForms();
    private final List<User> userFinder = new ArrayList<>();
    private final MenuNavigator navigator = new MenuNavigator();
    private final AuthService authService = new AuthService();

    private final SelfUpdadeStrategy strategy = new SelfUpdadeStrategy();
    private final UpdateForms updateForms = new UpdateForms(strategy, strategy);

    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

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
        System.out.println("1. Interno (STOKIST)");
        System.out.println("2. Externo (CONSUMER)");
        System.out.println("0. Voltar.");
    }

    public void findlUserMenu(String label) {
        System.out.println("\n####################################");
        System.out.println("            Buscar por Usuários " + label);
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

    public void updatePersonalOptions() {
        System.out.println("\n#########################################");
        System.out.println("      Atualizar informações do usuário");
        System.out.println("Escolha uma opção");
        System.out.println("1. Nome | 2. Data de nascimento | 0. Voltar");
    }

    public void profileOptions() {
        System.out.println("--------------------------------------");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Editar dados cadastrais");
        System.out.println("2. " + optionStatusLabel());
        System.out.println("3. Redefinir Senha do Usuário");
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

    private void findInternal(Supplier<List<User>> searchStrategy, UserRole role) {
        List<User> users = searchStrategy.get()
                .stream()
                .filter(user -> user.getUserRole() == role)
                .toList();

        if (users.isEmpty()) {
            System.out.println("\n--------------------------------------");
            System.out.println("Nenhum usuário encontrado.");
            System.out.println("--------------------------------------");

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
            System.out.println("\n--------------------------------------");
            System.out.println("[ERRO] ID inválido");
            System.out.println("Selecione um usuário da lista exibida.");
            System.out.println("--------------------------------------");
            return;
        }
        selectedUser = user;
        selectedUserFlow();
    }

    public void updateStatus(int option) {
        updateForms.updateStatus(option, selectedUser);
    }

    public void registerUser(int option) {
        System.out.println("Iniciando Cadastro... ");

        UserRegistrationStrategy registrationUserStrategy;

        if (option == 1) {
            registrationUserStrategy = new RegistrationStrategy();
        } else if (option == 2) {
            registrationUserStrategy = new SelfRegistrationStrategy();
        } else {
            throw new IllegalArgumentException("Opção inválida");
        }

        RegisterForm registerForm = new RegisterForm(registrationUserStrategy);
        registerForm.show();
    }

    @Override
    public void setPath() {
        navigator.navigate(
                this::showMenu,
                Map.of(
                        1, this::findMenuFlow,
                        2, this::registerFlow,
                        0, () -> {
                            System.out.println("Desconectando ... ");
                            authService.logout();
                            navigator.stop();
                        }));
    }

    public void findMenuFlow() {
        navigator.navigate(
                this::findMenu,
                Map.of(
                        1, this::findInternalFlow,
                        2, this::findExternalFlow,
                        0, () -> {
                        }));
    }

    public void findInternalFlow() {
        navigator.navigate(
                () -> findlUserMenu("Internos"),
                Map.of(
                        1, () -> findInternal(searchForms::searchByNameForm, UserRole.STOKIST),
                        2, () -> findInternal(searchForms::searchByEmailForm, UserRole.STOKIST),
                        3, () -> findByStatusFlow(UserRole.STOKIST),
                        0, () -> {
                        }));
    }

    public void findExternalFlow() {
        navigator.navigate(
                () -> findlUserMenu("Externos"),
                Map.of(
                        1, () -> findInternal(searchForms::searchByNameForm, UserRole.CONSUMER),
                        2, () -> findInternal(searchForms::searchByEmailForm, UserRole.CONSUMER),
                        3, () -> findByStatusFlow(UserRole.CONSUMER),
                        0, () -> {
                        }));
    }

    public void findByStatusFlow(UserRole role) {
        navigator.navigate(
                this::findByStatusMenu,
                Map.of(
                        1, () -> findInternal(() -> searchForms.searchByStatusForm(EntityStatus.ACTIVE), role),
                        2, () -> findInternal(() -> searchForms.searchByStatusForm(EntityStatus.INACTIVE), role),
                        3, () -> findInternal(() -> searchForms.searchByStatusForm(EntityStatus.LOCKED), role),
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
                        1, this::updatePersonalFlow,
                        2, this::updateStatusFlow,
                        3, () -> updateForms.passwordForm(getSelectedUser()),
                        0, () -> {
                        }));
    }

    public void updatePersonalFlow() {
        navigator.navigate(
                this::updatePersonalOptions,
                Map.of(
                        1, () -> updateForms.nameForm(getSelectedUser()),
                        2, () -> updateForms.birthForm(getSelectedUser()),
                        0, () -> {
                        }));
    }

    public void updateStatusFlow() {
        navigator.navigate(
                this::updateStatusMenu,
                Map.of(
                        1, () -> updateStatus(1),
                        2, () -> updateStatus(2),
                        0, () -> {

                        }));
    }

    public void registerFlow() {
        navigator.navigate(
                this::findMenu,
                Map.of(
                        1, () -> registerUser(1),
                        2, () -> registerUser(2),
                        0, () -> {
                        }));
    }

}
