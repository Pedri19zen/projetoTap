package projetoFinal.persistencia.mock;

import java.io.File;
import java.util.ArrayList;
import projetoFinal.modelo.Animal;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Veterinario;
import projetoFinal.persistencia.txt.GestorAnimaisTxt;
import projetoFinal.persistencia.txt.GestorClientesTxt;
import projetoFinal.persistencia.txt.GestorIntervencoesTxt;
import projetoFinal.persistencia.txt.GestorVeterinariosTxt;

public class DadosIniciais {
    private static final String PASTA_MOCK = "mock";

    public static void popular(Clinica clinica) {
        if (jaTemDados(clinica)) {
            return;
        }
        try {
            ArrayList<Veterinario> veterinarios = GestorVeterinariosTxt.ler(caminhoMock("veterinarios.txt"));
            ArrayList<Cliente> clientes = GestorClientesTxt.ler(caminhoMock("clientes.txt"));
            ArrayList<Animal> animais = GestorAnimaisTxt.ler(caminhoMock("animais.txt"), clientes);
            ArrayList<Intervencao> intervencoes = GestorIntervencoesTxt.ler(caminhoMock("intervencoes.txt"), veterinarios, clientes, animais);
            clinica.setVeterinarios(veterinarios);
            clinica.setClientes(clientes);
            clinica.setAnimais(animais);
            clinica.setIntervencoes(intervencoes);
        } catch (Exception e) {
            System.out.println("Sem dados iniciais para carregar (pasta '" + PASTA_MOCK + "' nao encontrada).");
        }
    }

    private static boolean jaTemDados(Clinica clinica) {
        return clinica.getVeterinarios().size() > 0
            || clinica.getClientes().size() > 0
            || clinica.getAnimais().size() > 0
            || clinica.getIntervencoes().size() > 0;
    }

    private static String caminhoMock(String nome) {
        return PASTA_MOCK + File.separator + nome;
    }
}
