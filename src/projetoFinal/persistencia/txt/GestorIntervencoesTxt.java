package projetoFinal.persistencia.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import projetoFinal.modelo.Animal;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Veterinario;

public class GestorIntervencoesTxt {

    public static void gravar(String caminho, ArrayList<Intervencao> lista) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        for (int i = 0; i < lista.size(); i++) {
            Intervencao in = lista.get(i);
            bw.write(in.getId() + ";" + in.getTipo() + ";" + in.getData() + ";" + in.getHora() + ";"
                + in.getVeterinario().getNif() + ";" + in.getAnimal().getId() + ";" + in.getCliente().getNif() + ";"
                + in.isComDeslocacao() + ";" + in.getDistanciaKm());
            bw.newLine();
        }
        bw.close();
    }

    public static ArrayList<Intervencao> ler(String caminho, ArrayList<Veterinario> veterinarios, ArrayList<Cliente> clientes, ArrayList<Animal> animais) throws IOException {
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        int numLinha = 0;
        while (linha != null) {
            numLinha++;
            try {
                String[] p = linha.split(";");
                if (p.length >= 9) {
                    Veterinario veterinario = Clinica.procurarVeterinarioNaLista(veterinarios, p[4]);
                    Animal animal = Clinica.procurarAnimalNaLista(animais, Integer.parseInt(p[5]));
                    Cliente cliente = Clinica.procurarClienteNaLista(clientes, p[6]);
                    if (veterinario != null && animal != null && cliente != null) {
                        Intervencao intervencao = new Intervencao(Integer.parseInt(p[0]), p[1], p[2], p[3], veterinario, animal, cliente, Boolean.parseBoolean(p[7]), Double.parseDouble(p[8]));
                        lista.add(intervencao);
                    }
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
