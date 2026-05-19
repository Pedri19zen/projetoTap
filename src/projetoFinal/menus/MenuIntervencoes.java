package projetoFinal.menus;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import projetoFinal.exibicao.Exibir;
import projetoFinal.leitura.Leitor;
import projetoFinal.leitura.LeitorIdentificacao;
import projetoFinal.leitura.LeitorTempo;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.regras.TiposIntervencao;
import projetoFinal.validacao.Validador;

public class MenuIntervencoes {
    private Clinica clinica;
    private Scanner sc;

    public MenuIntervencoes(Clinica clinica, Scanner sc) {
        this.clinica = clinica;
        this.sc = sc;
    }

    public void executar() {
        int opcao = -1;
        do {
            mostrarMenu();
            opcao = lerOpcao();
            executarOpcao(opcao);
        } while (opcao != 0);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("             INTERVENCOES");
        System.out.println("========================================");
        System.out.println("  1 - Listar intervencoes por data");
        System.out.println("  2 - Listar intervencoes de veterinario");
        System.out.println("  3 - Listar intervencoes de veterinario por data");
        System.out.println("  4 - Listar intervencoes passadas de animal");
        System.out.println("  5 - Listar intervencoes de hoje de animal");
        System.out.println("  6 - Listar intervencoes agendadas de animal");
        System.out.println();
        System.out.println("  0 - Voltar");
        System.out.println("========================================");
        System.out.print("Opcao: ");
    }

    private int lerOpcao() {
        int opcao = -1;
        boolean opcaoValida = false;
        do {
            try {
                String linha = sc.nextLine().trim();
                if (!Validador.textoNumerico(linha)) {
                    System.out.println("Opcao invalida.");
                    continue;
                }
                opcao = Integer.parseInt(linha);
                if (opcao >= 0 && opcao <= 6) {
                    opcaoValida = true;
                } else {
                    System.out.println("Opcao invalida.");
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (Exception e) {
                System.out.println("Opcao invalida.");
            }
        } while (!opcaoValida);
        return opcao;
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                listarPorData();
                break;
            case 2:
                listarDeVeterinario();
                break;
            case 3:
                listarDeVeterinarioPorData();
                break;
            case 4:
                listarPassadasAnimal();
                break;
            case 5:
                listarHojeAnimal();
                break;
            case 6:
                listarAgendadasAnimal();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    public void listarPorData() {
        String data = LeitorTempo.lerData(sc);
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getData().equals(data)) {
                lista.add(intervencoes.get(i));
            }
        }
        listarPorTipo(lista);
    }

    public void listarDeVeterinario() {
        Exibir.mostrarVeterinariosDisponiveis(clinica);
        String nifVet = LeitorIdentificacao.lerNif(sc, "NIF do veterinario:");
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getVeterinario().getNif().equals(nifVet)) {
                lista.add(intervencoes.get(i));
            }
        }
        listarPorTipo(lista);
    }

    public void listarDeVeterinarioPorData() {
        Exibir.mostrarVeterinariosDisponiveis(clinica);
        String nifVet = LeitorIdentificacao.lerNif(sc, "NIF do veterinario:");
        String data = LeitorTempo.lerData(sc);
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < intervencoes.size(); i++) {
            Intervencao intervencao = intervencoes.get(i);
            if (intervencao.getVeterinario().getNif().equals(nifVet) && intervencao.getData().equals(data)) {
                lista.add(intervencao);
            }
        }
        listarPorTipo(lista);
    }

    public void listarPassadasAnimal() {
        int idAnimal = Leitor.lerInteiroMinimo(sc, "Id do animal:", 1);
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        String dataHoje = clinica.getDataHoje();
        for (int i = 0; i < intervencoes.size(); i++) {
            Intervencao intervencao = intervencoes.get(i);
            if (intervencao.getAnimal().getId() == idAnimal && intervencao.getData().compareTo(dataHoje) < 0) {
                lista.add(intervencao);
            }
        }
        listarPorTipo(lista);
    }

    public void listarHojeAnimal() {
        int idAnimal = Leitor.lerInteiroMinimo(sc, "Id do animal:", 1);
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        String dataHoje = clinica.getDataHoje();
        for (int i = 0; i < intervencoes.size(); i++) {
            Intervencao intervencao = intervencoes.get(i);
            if (intervencao.getAnimal().getId() == idAnimal && intervencao.getData().equals(dataHoje)) {
                lista.add(intervencao);
            }
        }
        listarPorTipo(lista);
    }

    public void listarAgendadasAnimal() {
        int idAnimal = Leitor.lerInteiroMinimo(sc, "Id do animal:", 1);
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        String dataHoje = clinica.getDataHoje();
        for (int i = 0; i < intervencoes.size(); i++) {
            Intervencao intervencao = intervencoes.get(i);
            if (intervencao.getAnimal().getId() == idAnimal && intervencao.getData().compareTo(dataHoje) > 0) {
                lista.add(intervencao);
            }
        }
        listarPorTipo(lista);
    }

    private void listarPorTipo(ArrayList<Intervencao> lista) {
        ArrayList<String> tipos = TiposIntervencao.obter();
        int total = 0;
        for (int i = 0; i < tipos.size(); i++) {
            String tipo = tipos.get(i);
            System.out.println("Tipo: " + tipo);
            int contador = 0;
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(j).getTipo().equals(tipo)) {
                    System.out.println("  " + lista.get(j));
                    contador++;
                    total++;
                }
            }
            if (contador == 0) {
                System.out.println("  Sem intervencoes.");
            }
        }
        if (total == 0) {
            System.out.println("Sem resultados.");
        }
    }
}
