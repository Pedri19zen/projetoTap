package projetoFinal.leitura;

import java.util.Scanner;
import projetoFinal.validacao.Validador;

public class LeitorTempo {

    public static String lerData(Scanner sc) {
        String data = "";
        boolean valido = false;
        do {
            System.out.println("Data (YYYY-MM-DD):");
            data = sc.nextLine().trim();
            if (Validador.dataValida(data)) {
                valido = true;
            } else {
                System.out.println("Data invalida. Tente novamente.");
            }
        } while (!valido);
        return data;
    }

    public static String lerHora(Scanner sc) {
        String hora = "";
        boolean valido = false;
        do {
            System.out.println("Hora (HH:MM):");
            hora = sc.nextLine().trim();
            if (Validador.horaValida(hora)) {
                valido = true;
            } else {
                System.out.println("Hora invalida. Tente novamente.");
            }
        } while (!valido);
        return hora;
    }
}
