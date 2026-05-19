package projetoFinal.menus;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import projetoFinal.modelo.Animal;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Veterinario;
import projetoFinal.regras.TiposIntervencao;
import projetoFinal.validacao.Validador;

public class MenuListagens {
    private Clinica clinica;
    private Scanner sc;

    public MenuListagens(Clinica clinica, Scanner sc) {
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
        System.out.println("              LISTAGENS");
        System.out.println("========================================");
        System.out.println("  1 - Listar veterinarios");
        System.out.println("  2 - Listar veterinarios e clientes");
        System.out.println("  3 - Listar veterinarios e animais");
        System.out.println("  4 - Listar clientes e animais");
        System.out.println("  5 - Listar animais e donos");
        System.out.println("  6 - Listar tipos de intervencao");
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
                listarVeterinarios();
                break;
            case 2:
                listarVeterinariosEClientes();
                break;
            case 3:
                listarVeterinariosEAnimais();
                break;
            case 4:
                listarClientesEAnimais();
                break;
            case 5:
                listarAnimaisEDonos();
                break;
            case 6:
                listarTiposIntervencao();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    public void listarVeterinarios() {
        System.out.println();
        System.out.println("--- Lista de veterinarios ---");
        ArrayList<Veterinario> veterinarios = clinica.getVeterinarios();
        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println(veterinarios.get(i));
        }
        if (veterinarios.size() == 0) {
            System.out.println("Sem veterinarios.");
        }
    }

    public void listarVeterinariosEClientes() {
        System.out.println();
        System.out.println("--- Veterinarios e clientes ---");
        ArrayList<Veterinario> veterinarios = clinica.getVeterinarios();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < veterinarios.size(); i++) {
            Veterinario vet = veterinarios.get(i);
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            for (int j = 0; j < intervencoes.size(); j++) {
                Intervencao intervencao = intervencoes.get(j);
                if (intervencao.getVeterinario().getNif().equals(vet.getNif())) {
                    if (!existeClienteNaLista(lista, intervencao.getCliente().getNif())) {
                        lista.add(intervencao.getCliente());
                    }
                }
            }
            System.out.println(vet);
            for (int j = 0; j < lista.size(); j++) {
                System.out.println("  " + lista.get(j));
            }
            if (lista.size() == 0) {
                System.out.println("  Sem clientes.");
            }
        }
    }

    public void listarVeterinariosEAnimais() {
        System.out.println();
        System.out.println("--- Veterinarios e animais ---");
        ArrayList<Veterinario> veterinarios = clinica.getVeterinarios();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < veterinarios.size(); i++) {
            Veterinario vet = veterinarios.get(i);
            ArrayList<Animal> lista = new ArrayList<Animal>();
            for (int j = 0; j < intervencoes.size(); j++) {
                Intervencao intervencao = intervencoes.get(j);
                if (intervencao.getVeterinario().getNif().equals(vet.getNif())) {
                    if (!existeAnimalNaLista(lista, intervencao.getAnimal().getId())) {
                        lista.add(intervencao.getAnimal());
                    }
                }
            }
            System.out.println(vet);
            for (int j = 0; j < lista.size(); j++) {
                System.out.println("  " + lista.get(j));
            }
            if (lista.size() == 0) {
                System.out.println("  Sem animais.");
            }
        }
    }

    public void listarClientesEAnimais() {
        System.out.println();
        System.out.println("--- Clientes e animais ---");
        ArrayList<Cliente> clientes = clinica.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println(cliente);
            for (int j = 0; j < cliente.getAnimais().size(); j++) {
                System.out.println("  " + cliente.getAnimais().get(j));
            }
            if (cliente.getAnimais().size() == 0) {
                System.out.println("  Sem animais.");
            }
        }
    }

    public void listarAnimaisEDonos() {
        System.out.println();
        System.out.println("--- Animais e donos ---");
        ArrayList<Animal> animais = clinica.getAnimais();
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            Cliente cliente = clinica.procurarDonoAnimal(animal.getId());
            System.out.println(animal);
            if (cliente != null) {
                System.out.println("  Dono: " + cliente);
            } else {
                System.out.println("  Dono nao encontrado.");
            }
        }
    }

    public void listarTiposIntervencao() {
        System.out.println();
        System.out.println("--- Tipos de intervencao ---");
        ArrayList<String> tipos = TiposIntervencao.obter();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println("  - " + tipos.get(i));
        }
    }

    private boolean existeClienteNaLista(ArrayList<Cliente> lista, String nif) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNif().equals(nif)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeAnimalNaLista(ArrayList<Animal> lista, int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}
