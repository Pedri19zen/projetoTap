package projetoFinal.leitura;

import java.util.Scanner;
import projetoFinal.regras.TiposIntervencao;

public class LeitorIntervencao {

    public static String lerTipo(Scanner sc) {
        String tipo = "";
        boolean valido = false;
        do {
            System.out.println("Tipo (consulta, vacina, cirurgia):");
            tipo = sc.nextLine().trim().toLowerCase();
            if (TiposIntervencao.valido(tipo)) {
                valido = true;
            } else {
                System.out.println("Tipo invalido. Tente novamente.");
            }
        } while (!valido);
        return tipo;
    }
}
