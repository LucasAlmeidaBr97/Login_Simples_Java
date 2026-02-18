package ui;

import model.User;

public class AdminMenu implements Menu {
    private final SearchForms searchForms = new SearchForms();

    @Override
    public void showMenu() {
        System.out.println("\n####################################");
        System.out.println("            Painel do ADMIN");
        System.out.println("1. Buscar Usuário");
        System.out.println("3. Cadastrar Nono Usuário");
        System.out.println("5. Resetar Senha de Usuário");
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

    public void findInternalByName() {
        User user = searchForms.searchByNameForm();
        if (user == null) {
            System.out.println("Usuário não encontrado.");
        } else {
            System.out.println("Usuário encontrado:");
            System.out.println(user);
        }
    }

    

    @Override
    public void setPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPath'");
    }

}
