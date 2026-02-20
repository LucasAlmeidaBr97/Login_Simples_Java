package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import auth.AuthService;
import model.User;
import model.enums.EntityStatus;

public class AdminMenu implements Menu {
    private final SearchForms searchForms = new SearchForms();
    private final List<User> userFinder = new ArrayList<>();
    private final MenuNavigator navigator = new MenuNavigator();
    private final AuthService authService = new AuthService();

    @Override
    public void showMenu() {
        System.out.println("\n####################################");
        System.out.println("            Painel do ADMIN");
        System.out.println("1. Buscar Usuário");
        System.out.println("2. Cadastrar Nono Usuário");
        System.out.println("3. Resetar Senha de Usuário");
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

    private void findInternal(Supplier<List<User>> searchStrategy) {
        List<User> users = searchStrategy.get();

        if (users.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            userFinder.clear();
            userFinder.addAll(users);
            showUsers();
        }
    }

    public void showUsers() {
        System.out.println("\n####################################");
        System.out.println("            Usuários encontrados");
        for (User user : userFinder) {
            System.out.println("--------------------------------------");
            System.out.println(user);
            System.out.println("--------------------------------------");
        }
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

}
