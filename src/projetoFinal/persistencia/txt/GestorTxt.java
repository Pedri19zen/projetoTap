package projetoFinal.persistencia.txt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import projetoFinal.modelo.Animal;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Veterinario;

public class GestorTxt {

    public static void gravar(Clinica clinica) {
        try {
            clinica.garantirPastaDados();
            GestorVeterinariosTxt.gravar(clinica.caminho("veterinarios.txt"), clinica.getVeterinarios());
            GestorClientesTxt.gravar(clinica.caminho("clientes.txt"), clinica.getClientes());
            GestorAnimaisTxt.gravar(clinica.caminho("animais.txt"), clinica.getAnimais(), clinica);
            GestorIntervencoesTxt.gravar(clinica.caminho("intervencoes.txt"), clinica.getIntervencoes());
        } catch (IOException e) {
            System.out.println("Erro ao gravar txt.");
        }
    }

    public static boolean ler(Clinica clinica) {
        if (!todosExistem(clinica)) {
            return false;
        }
        try {
            ArrayList<Veterinario> veterinarios = GestorVeterinariosTxt.ler(clinica.caminho("veterinarios.txt"));
            ArrayList<Cliente> clientes = GestorClientesTxt.ler(clinica.caminho("clientes.txt"));
            ArrayList<Animal> animais = GestorAnimaisTxt.ler(clinica.caminho("animais.txt"), clientes);
            ArrayList<Intervencao> intervencoes = GestorIntervencoesTxt.ler(clinica.caminho("intervencoes.txt"), veterinarios, clientes, animais);
            clinica.setVeterinarios(veterinarios);
            clinica.setClientes(clientes);
            clinica.setAnimais(animais);
            clinica.setIntervencoes(intervencoes);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao ler txt.");
            return false;
        }
    }

    private static boolean todosExistem(Clinica clinica) {
        return new File(clinica.caminho("veterinarios.txt")).exists()
            && new File(clinica.caminho("clientes.txt")).exists()
            && new File(clinica.caminho("animais.txt")).exists()
            && new File(clinica.caminho("intervencoes.txt")).exists();
    }
}
