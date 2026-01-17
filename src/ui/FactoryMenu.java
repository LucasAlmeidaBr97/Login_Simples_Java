package ui;

import model.enums.UserRole;

public class FactoryMenu {

    public static Menu createMenu(UserRole role) {
         return switch (role) {
            case ADMIN -> new AdminMenu();
            case CONSUMER -> new ConsumerMenu();
            case STOKIST -> new StokistMenu();
            default -> throw new IllegalArgumentException("Papel de usuário desconhecido.");
        };
    }

}
