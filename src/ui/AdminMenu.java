package ui;

public class AdminMenu implements Menu {

    @Override
    public void showMenu() {
    System.out.println("\n####################################");
    System.out.println("            Painel do ADMIN");
    System.out.println("1. Meu Perfil");
    System.out.println("2. Listar Usuários");
    System.out.println("3. Criar Conta (ADMIN/STOKIST)");
    System.out.println("4. Ativar / Desativar Usuário");
    System.out.println("5. Resetar Senha de Usuário");
    System.out.println("6. Listar STOKIST");
    System.out.println("7. Bloquear / Desbloquear Usuário");
    System.out.println("0. Logout");
    System.out.print("Escolha: ");
}

    @Override
    public void setPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPath'");
    }
    
}
