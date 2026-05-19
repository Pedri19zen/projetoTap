package projetoFinal.modelo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

// classe principal que guarda toda a informacao da clinica:
// listas de veterinarios, clientes, animais e intervencoes
public class Clinica implements Serializable {
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Cliente> clientes;
    private ArrayList<Animal> animais;
    private ArrayList<Intervencao> intervencoes;
    // data simulada de "hoje" - usada para distinguir intervencoes passadas de agendadas
    private String dataHoje = "2026-04-23";
    private String pastaDados = "dados";

    public Clinica() {
        veterinarios = new ArrayList<Veterinario>();
        clientes = new ArrayList<Cliente>();
        animais = new ArrayList<Animal>();
        intervencoes = new ArrayList<Intervencao>();
    }

    public ArrayList<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public ArrayList<Intervencao> getIntervencoes() {
        return intervencoes;
    }

    public String getDataHoje() {
        return dataHoje;
    }

    public String getPastaDados() {
        return pastaDados;
    }

    public void setVeterinarios(ArrayList<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }

    public void setIntervencoes(ArrayList<Intervencao> intervencoes) {
        this.intervencoes = intervencoes;
    }

    public void setDataHoje(String dataHoje) {
        if (dataHoje != null) {
            this.dataHoje = dataHoje;
        }
    }

    public void setPastaDados(String pastaDados) {
        if (pastaDados != null) {
            this.pastaDados = pastaDados;
        }
    }

    public String caminho(String nome) {
        return pastaDados + File.separator + nome;
    }

    public void garantirPastaDados() {
        File pasta = new File(pastaDados);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public Veterinario procurarVeterinarioPorNif(String nif) {
        return procurarVeterinarioNaLista(veterinarios, nif);
    }

    public Cliente procurarClientePorNif(String nif) {
        return procurarClienteNaLista(clientes, nif);
    }

    public Animal procurarAnimalPorId(int id) {
        return procurarAnimalNaLista(animais, id);
    }

    public Intervencao procurarIntervencaoPorId(int id) {
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getId() == id) {
                return intervencoes.get(i);
            }
        }
        return null;
    }

    public Cliente procurarDonoAnimal(int idAnimal) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            for (int j = 0; j < cliente.getAnimais().size(); j++) {
                if (cliente.getAnimais().get(j).getId() == idAnimal) {
                    return cliente;
                }
            }
        }
        return null;
    }

    public static Veterinario procurarVeterinarioNaLista(ArrayList<Veterinario> lista, String nif) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNif().equals(nif)) {
                return lista.get(i);
            }
        }
        return null;
    }

    public static Cliente procurarClienteNaLista(ArrayList<Cliente> lista, String nif) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNif().equals(nif)) {
                return lista.get(i);
            }
        }
        return null;
    }

    public static Animal procurarAnimalNaLista(ArrayList<Animal> lista, int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return lista.get(i);
            }
        }
        return null;
    }

    // procura o maior id existente e devolve +1, para nao haver ids repetidos
    public int proximoIdAnimal() {
        int maior = 0;
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() > maior) {
                maior = animais.get(i).getId();
            }
        }
        return maior + 1;
    }

    public int proximoIdIntervencao() {
        int maior = 0;
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getId() > maior) {
                maior = intervencoes.get(i).getId();
            }
        }
        return maior + 1;
    }
}
