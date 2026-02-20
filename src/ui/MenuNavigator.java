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

    public void navigate(Runnable showMenu, Map<Integer, Runnable> actions) { 

        running = true;
        while (running) {
            showMenu.run();
            try {
                int option = scan.nextInt();
                scan.nextLine();
                Runnable action = actions.get(option);

                if (action == null) {
                    System.out.println("Opção inválida");
                    continue;
                }

                action.run(); 

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
