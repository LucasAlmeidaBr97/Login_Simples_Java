package ui;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MenuNavigator {

    private final Scanner scan = new Scanner(System.in);
    private boolean running = true;

    public void stop() {
        running = false;
    }

    public void navigate(Runnable showMenu, Map<Integer, Runnable> actions) { // < - map actions encapsula ações.

        running = true;
        while (running) {
            showMenu.run();
            try {
                int option = scan.nextInt();
                scan.nextLine();
                Runnable action = actions.get(option); // <- busca a ação associada a chave informada.

                if (action == null) {
                    System.out.println("Opção inválida");
                    continue;
                }

                action.run(); // <-- executa a ação

                if (option == 0) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: entrada inválida!");
                scan.nextLine();
            }
        }

    }

}
