package service.strategy;

import model.User;

public class SelfRegistrationStrategy implements FormStrategy {

    @Override
    public void setUserData(User user, String password) {
        System.out.println("Nome: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Data de Nascimento: " + user.getBirthDate());
        System.out.println("Papel de Usuário: " + user.getUserRole());
        System.out.println("Status: " + user.getStatus());
        System.out.println("Senha: " + password);       
    }

    @Override
    public boolean isRoleEditable() {
        return false;
    }
    
}
