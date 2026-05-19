package projetoFinal.leitura;

import java.util.Scanner;
import projetoFinal.modelo.Contacto;
import projetoFinal.validacao.Validador;

public class LeitorContacto {

    public static Contacto lerContacto(Scanner sc) {
        String email = lerEmail(sc);
        String telefone = lerTelefone(sc);
        String metodo = lerMetodoPreferido(sc);
        return new Contacto(email, telefone, metodo);
    }

    public static String lerEmail(Scanner sc) {
        String email = "";
        boolean valido = false;
        do {
            System.out.println("Email:");
            email = sc.nextLine().trim();
            if (Validador.emailValido(email)) {
                valido = true;
            } else {
                System.out.println("Email invalido. Tente novamente.");
            }
        } while (!valido);
        return email;
    }

    public static String lerTelefone(Scanner sc) {
        String telefone = "";
        boolean valido = false;
        do {
            System.out.println("Telefone:");
            telefone = sc.nextLine().trim();
            if (Validador.telefoneValido(telefone)) {
                valido = true;
            } else {
                System.out.println("Telefone invalido (9 digitos, comecar por 2 ou 9). Tente novamente.");
            }
        } while (!valido);
        return telefone;
    }

    public static String lerMetodoPreferido(Scanner sc) {
        String metodo = "";
        boolean valido = false;
        do {
            System.out.println("Metodo preferido (email/telefone):");
            metodo = sc.nextLine().trim().toLowerCase();
            if (Validador.metodoContactoValido(metodo)) {
                valido = true;
            } else {
                System.out.println("Metodo invalido. Use 'email' ou 'telefone'.");
            }
        } while (!valido);
        return metodo;
    }
}
