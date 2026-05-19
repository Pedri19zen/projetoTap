package projetoFinal.leitura;

import java.util.Scanner;
import projetoFinal.validacao.Validador;

public class LeitorIdentificacao {

    public static String lerNif(Scanner sc, String mensagem) {
        String nif = "";
        boolean valido = false;
        do {
            System.out.println(mensagem);
            nif = sc.nextLine().trim();
            if (Validador.nifValido(nif)) {
                valido = true;
            } else {
                System.out.println("NIF invalido (precisa de 9 digitos). Tente novamente.");
            }
        } while (!valido);
        return nif;
    }

    public static String lerCodigoPostal(Scanner sc) {
        String codigoPostal = "";
        boolean valido = false;
        do {
            System.out.println("Codigo postal (xxxx-xxx):");
            codigoPostal = sc.nextLine().trim();
            if (Validador.codigoPostalValido(codigoPostal)) {
                valido = true;
            } else {
                System.out.println("Codigo postal invalido. Tente novamente.");
            }
        } while (!valido);
        return codigoPostal;
    }
}
