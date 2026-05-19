package projetoFinal.persistencia.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import projetoFinal.modelo.Contacto;
import projetoFinal.modelo.Veterinario;

public class GestorVeterinariosTxt {

    public static void gravar(String caminho, ArrayList<Veterinario> lista) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        for (int i = 0; i < lista.size(); i++) {
            Veterinario v = lista.get(i);
            Contacto c = v.getContacto();
            bw.write(v.getNif() + ";" + v.getNome() + ";" + v.getIdOrdem() + ";"
                + c.getEmail() + ";" + c.getTelefone() + ";" + c.getMetodoPreferido());
            bw.newLine();
        }
        bw.close();
    }

    public static ArrayList<Veterinario> ler(String caminho) throws IOException {
        ArrayList<Veterinario> lista = new ArrayList<Veterinario>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        int numLinha = 0;
        while (linha != null) {
            numLinha++;
            try {
                String[] p = linha.split(";");
                if (p.length >= 6) {
                    Contacto contacto = new Contacto(p[3], p[4], p[5]);
                    lista.add(new Veterinario(p[0], p[1], p[2], contacto));
                }
            } catch (Exception e) {
                System.out.println("Linha " + numLinha + " ignorada em " + caminho);
            }
            linha = br.readLine();
        }
        br.close();
        return lista;
    }
}
