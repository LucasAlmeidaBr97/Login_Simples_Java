package ui;

import java.util.List;
import java.util.Scanner;

import model.User;
import service.UserService;
import util.Validator;

public class SearchForms {

    private final Validator validator = new Validator();
    private final Scanner scan = new Scanner(System.in);
    private final UserService userService = new UserService();

    public List<User> searchByNameForm() {
        System.out.println("Digite o nome do usuário a ser buscado: ");
        String name = validator.validateName(scan.nextLine());
        return userService.getUserByName(name);
    }

}
