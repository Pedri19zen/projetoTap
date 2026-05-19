package projetoFinal.menus;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.regras.TiposIntervencao;
import projetoFinal.validacao.Validador;

public class MenuFaturacao {
    private Clinica clinica;
    private Scanner sc;

    public MenuFaturacao(Clinica clinica, Scanner sc) {
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
        System.out.println("              FATURACAO");
        System.out.println("========================================");
        System.out.println("  1 - Listar faturacao efetuada");
        System.out.println("  2 - Listar faturacao agendada");
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
                if (opcao >= 0 && opcao <= 2) {
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
                listarEfetuada();
                break;
            case 2:
                listarAgendada();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    public void listarEfetuada() {
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        String dataHoje = clinica.getDataHoje();
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getData().compareTo(dataHoje) <= 0) {
                lista.add(intervencoes.get(i));
            }
        }
        listarFaturacao(lista);
    }

    public void listarAgendada() {
        ArrayList<Intervencao> lista = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        String dataHoje = clinica.getDataHoje();
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getData().compareTo(dataHoje) > 0) {
                lista.add(intervencoes.get(i));
            }
        }
        listarFaturacao(lista);
    }

    private void listarFaturacao(ArrayList<Intervencao> lista) {
        System.out.println();
        System.out.println("--- Por tipo ---");
        ArrayList<String> tipos = TiposIntervencao.obter();
        for (int i = 0; i < tipos.size(); i++) {
            String tipo = tipos.get(i);
            double soma = 0.0;
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(j).getTipo().equals(tipo)) {
                    soma = soma + lista.get(j).calcularCusto();
                }
            }
            System.out.println("  " + tipo + ": " + soma);
        }
        System.out.println();
        System.out.println("--- Por animal ---");
        ArrayList<Integer> idsAnimais = new ArrayList<Integer>();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getAnimal().getId();
            if (!contemInt(idsAnimais, id)) {
                idsAnimais.add(id);
                double soma = 0.0;
                for (int j = 0; j < lista.size(); j++) {
                    if (lista.get(j).getAnimal().getId() == id) {
                        soma = soma + lista.get(j).calcularCusto();
                    }
                }
                System.out.println("  " + lista.get(i).getAnimal().getNome() + " (id " + id + "): " + soma);
            }
        }
        System.out.println();
        System.out.println("--- Por cliente ---");
        ArrayList<String> nifsClientes = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            String nif = lista.get(i).getCliente().getNif();
            if (!contemTexto(nifsClientes, nif)) {
                nifsClientes.add(nif);
                double soma = 0.0;
                for (int j = 0; j < lista.size(); j++) {
                    if (lista.get(j).getCliente().getNif().equals(nif)) {
                        soma = soma + lista.get(j).calcularCusto();
                    }
                }
                System.out.println("  " + lista.get(i).getCliente().getNome() + " (nif " + nif + "): " + soma);
            }
        }
        double total = 0.0;
        for (int i = 0; i < lista.size(); i++) {
            total = total + lista.get(i).calcularCusto();
        }
        System.out.println();
        System.out.println("Soma total: " + total);
    }

    private boolean contemInt(ArrayList<Integer> lista, int valor) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) == valor) {
                return true;
            }
        }
        return false;
    }

    private boolean contemTexto(ArrayList<String> lista, String valor) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(valor)) {
                return true;
            }
        }
        return false;
    }
}
