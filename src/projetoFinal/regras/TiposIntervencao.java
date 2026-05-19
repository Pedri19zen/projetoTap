package projetoFinal.regras;

import java.util.ArrayList;

public class TiposIntervencao {

    public static ArrayList<String> obter() {
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("consulta");
        tipos.add("vacina");
        tipos.add("cirurgia");
        return tipos;
    }

    public static boolean valido(String tipo) {
        ArrayList<String> tipos = obter();
        for (int i = 0; i < tipos.size(); i++) {
            if (tipos.get(i).equals(tipo)) {
                return true;
            }
        }
        return false;
    }
}
