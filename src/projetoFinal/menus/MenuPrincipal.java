package projetoFinal.menus;

import java.util.NoSuchElementException;
import java.util.Scanner;
import projetoFinal.modelo.Clinica;
import projetoFinal.persistencia.dat.GestorDat;
import projetoFinal.persistencia.mock.DadosIniciais;
import projetoFinal.persistencia.txt.GestorTxt;
import projetoFinal.validacao.Validador;

public class MenuPrincipal {
    private Scanner sc;
    private Clinica clinica;
    private MenuListagens menuListagens;
    private MenuIntervencoes menuIntervencoes;
    private MenuFaturacao menuFaturacao;
    private MenuGestao menuGestao;

    public MenuPrincipal(Scanner sc) {
        this.sc = sc;
        this.clinica = new Clinica();
        this.menuListagens = new MenuListagens(clinica, sc);
        this.menuIntervencoes = new MenuIntervencoes(clinica, sc);
        this.menuFaturacao = new MenuFaturacao(clinica, sc);
        this.menuGestao = new MenuGestao(clinica, sc);
    }

    public void executar() {
        boolean carregou = GestorDat.ler(clinica);
        if (!carregou) {
            GestorTxt.ler(clinica);
        }
        DadosIniciais.popular(clinica);
        int opcao = -1;
        try {
            do {
                mostrarMenu();
                opcao = lerOpcao();
                executarOpcao(opcao);
                if (opcao != 0) {
                    GestorTxt.gravar(clinica);
                    GestorDat.gravar(clinica);
                }
            } while (opcao != 0);
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Entrada fechada. A guardar e sair.");
        }
        GestorTxt.gravar(clinica);
        GestorDat.gravar(clinica);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("                 MENU");
        System.out.println("========================================");
        System.out.println("  1 - Listagens");
        System.out.println("  2 - Intervencoes");
        System.out.println("  3 - Faturacao");
        System.out.println("  4 - Gestao");
        System.out.println();
        System.out.println("  0 - Sair");
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
                if (opcao >= 0 && opcao <= 4) {
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
                menuListagens.executar();
                break;
            case 2:
                menuIntervencoes.executar();
                break;
            case 3:
                menuFaturacao.executar();
                break;
            case 4:
                menuGestao.executar();
                break;
            case 0:
                System.out.println("A sair.");
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }
}
