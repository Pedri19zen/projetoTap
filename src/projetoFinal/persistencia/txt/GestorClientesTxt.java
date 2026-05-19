package projetoFinal.persistencia.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Contacto;
import projetoFinal.modelo.Morada;

public class GestorClientesTxt {

    public static void gravar(String caminho, ArrayList<Cliente> lista) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            Morada m = c.getMorada();
            Contacto co = c.getContacto();
            bw.write(c.getNif() + ";" + c.getNome() + ";"
                + m.getRua() + ";" + m.getNumero() + ";" + m.getCodigoPostal() + ";" + m.getLocalidade() + ";"
                + co.getEmail() + ";" + co.getTelefone() + ";" + co.getMetodoPreferido() + ";"
                + c.isAtivo());
            bw.newLine();
        }
        bw.close();
    }

    public static ArrayList<Cliente> ler(String caminho) throws IOException {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        int numLinha = 0;
        while (linha != null) {
            numLinha++;
            try {
                String[] p = linha.split(";");
                if (p.length >= 10) {
                    Morada morada = new Morada(p[2], Integer.parseInt(p[3]), p[4], p[5]);
                    Contacto contacto = new Contacto(p[6], p[7], p[8]);
                    Cliente cliente = new Cliente(p[0], p[1], morada, contacto, Boolean.parseBoolean(p[9]));
                    lista.add(cliente);
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
