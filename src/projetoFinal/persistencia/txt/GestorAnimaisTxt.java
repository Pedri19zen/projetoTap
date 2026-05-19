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

public class GestorAnimaisTxt {

    public static void gravar(String caminho, ArrayList<Animal> animais, Clinica clinica) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        for (int i = 0; i < animais.size(); i++) {
            Animal a = animais.get(i);
            Cliente dono = clinica.procurarDonoAnimal(a.getId());
            String nifCliente = "";
            if (dono != null) {
                nifCliente = dono.getNif();
            }
            bw.write(a.getId() + ";" + a.getNome() + ";" + a.getEspecie() + ";" + a.getGenero() + ";" + a.getPeso() + ";" + a.isAtivo() + ";" + nifCliente);
            bw.newLine();
        }
        bw.close();
    }

    public static ArrayList<Animal> ler(String caminho, ArrayList<Cliente> clientes) throws IOException {
        ArrayList<Animal> lista = new ArrayList<Animal>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        int numLinha = 0;
        while (linha != null) {
            numLinha++;
            try {
                String[] p = linha.split(";");
                if (p.length >= 7) {
                    Animal animal = new Animal(Integer.parseInt(p[0]), p[1], p[2], p[3], Double.parseDouble(p[4]), Boolean.parseBoolean(p[5]));
                    lista.add(animal);
                    Cliente cliente = Clinica.procurarClienteNaLista(clientes, p[6]);
                    if (cliente != null) {
                        cliente.addAnimal(animal);
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
