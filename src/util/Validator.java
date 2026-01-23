package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {

    private static final String nameRegex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\\\s]+$";
    private static final String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*().]).{8,}$";
    static Scanner scan = new Scanner(System.in);

    public static boolean isValidName(String name) {
        return name != null && Pattern.matches(nameRegex, name) && name.trim().length() > 2;
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(emailRegex, email);
    }

    public static boolean isValidDate(String date) {
        try {
            formatter.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidPassword(String password) {
        return password != null && Pattern.matches(passwordRegex, password);
    }

    public static boolean isValidRole(String role) {
        return role != null && (role.equals("ADMIN") || role.equals("STOKIST"));
    }


    public static String validateName(String name) {
        while (true) {
            if (isValidName(name)) {
                return name;
            } else {
                System.out.println("Erro: Entrada inválida! Digite apenas letras.");
                System.out.print("Tente novamente: ");
                name = scan.nextLine();
            }
        }
    }

    public static String validateEmail(String email) {
        while (true) {
            if (isValidEmail(email)) {
                return email;
            } else {
                System.out.println("Erro: Formato de e-mail inválido (ex: usuario@email.com).");
                System.out.print("Tente novamente: ");
                email = scan.nextLine();
            }
        }
    }

    public static LocalDate validateDate(String date) {
        while (true) {
            if (isValidDate(date) && LocalDate.parse(date, formatter).isAfter(LocalDate.now())) {
                System.out.println("Erro: A data não pode ser no futuro!");
                System.out.print("Digite uma data de nascimento válida: ");
                date = scan.nextLine();
            } else if (isValidDate(date)) {
                return LocalDate.parse(date, formatter);
            } else {
                System.out.println("Erro: Formato de data inválido (dd/MM/yyyy).");
                System.out.print("Tente novamente: ");
                date = scan.nextLine();
            }
        }
    }

    public static String validatePassword(String password) {
        while (true) {
            if (isValidPassword(password)) {
                return password;
            } else {
                System.out.println("Erro Formato de senha invalido!");
                System.out.print("Tente novamente: ");
                password = scan.nextLine();
            }
        }
    }

    public static String validateRole(String role) {
        while (true) {
            if (isValidRole(role)) {
                return role;
            } else {
                System.out.println("Erro: Papel de usuário inválido (ADMIN, STOKIST).");
                System.out.print("Tente novamente: ");
                role = scan.nextLine().toUpperCase();
            }
        }
    }     

}
