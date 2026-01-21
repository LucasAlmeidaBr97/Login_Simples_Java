package ui;

import service.strategy.FormStrategy;

public class UserForm {
    private FormStrategy strategy;

    

    public UserForm(FormStrategy strategyy) {
        this.strategy = strategy;
    }

    public void showForm(){
        System.out.println("####################################");
        System.out.println("\n=== FORMULÁRIO DE CADASTRO ===");
        System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
        System.out.println("Digite seu email: ");
        System.out.println("Digite sua senha: ");
    }

}
