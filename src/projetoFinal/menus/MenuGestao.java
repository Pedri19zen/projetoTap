package projetoFinal.menus;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import projetoFinal.exibicao.Exibir;
import projetoFinal.leitura.Leitor;
import projetoFinal.leitura.LeitorContacto;
import projetoFinal.leitura.LeitorIdentificacao;
import projetoFinal.leitura.LeitorIntervencao;
import projetoFinal.leitura.LeitorTempo;
import projetoFinal.modelo.Animal;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Contacto;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Morada;
import projetoFinal.modelo.Veterinario;
import projetoFinal.regras.Disponibilidade;
import projetoFinal.validacao.Validador;

public class MenuGestao {
    private Clinica clinica;
    private Scanner sc;

    public MenuGestao(Clinica clinica, Scanner sc) {
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
        System.out.println("                GESTAO");
        System.out.println("========================================");
        System.out.println("  1 - Inserir veterinario");
        System.out.println("  2 - Agendar intervencao");
        System.out.println("  3 - Inserir cliente e animal");
        System.out.println("  4 - Inserir animal em cliente");
        System.out.println("  5 - Desativar animal");
        System.out.println("  6 - Emitir recibo");
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
                inserirVeterinario();
                break;
            case 2:
                agendarIntervencao();
                break;
            case 3:
                inserirClienteEAnimal();
                break;
            case 4:
                inserirAnimalEmCliente();
                break;
            case 5:
                desativarAnimal();
                break;
            case 6:
                emitirRecibo();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    public void inserirVeterinario() {
        System.out.println();
        System.out.println("--- Inserir veterinario ---");
        String nif = LeitorIdentificacao.lerNif(sc, "NIF:");
        if (clinica.procurarVeterinarioPorNif(nif) != null) {
            System.out.println("Veterinario ja existe.");
            return;
        }
        String nome = Leitor.lerTexto(sc, "Nome:");
        String idOrdem = Leitor.lerTexto(sc, "Id ordem:");
        Contacto contacto = LeitorContacto.lerContacto(sc);
        Veterinario veterinario = new Veterinario(nif, nome, idOrdem, contacto);
        clinica.getVeterinarios().add(veterinario);
        System.out.println("Veterinario inserido.");
    }

    public void agendarIntervencao() {
        System.out.println();
        System.out.println("--- Agendar intervencao ---");
        String tipo = LeitorIntervencao.lerTipo(sc);
        String data = LeitorTempo.lerData(sc);
        if (data.compareTo(clinica.getDataHoje()) < 0) {
            System.out.println("Data no passado. Use uma data igual ou superior a hoje.");
            return;
        }
        String hora = LeitorTempo.lerHora(sc);
        System.out.println();
        Exibir.mostrarVeterinariosDisponiveis(clinica);
        String nifVet = LeitorIdentificacao.lerNif(sc, "NIF do veterinario:");
        Veterinario veterinario = clinica.procurarVeterinarioPorNif(nifVet);
        if (veterinario == null) {
            System.out.println("Veterinario nao encontrado.");
            return;
        }
        System.out.println();
        Exibir.mostrarClientesDisponiveis(clinica);
        String nifCliente = LeitorIdentificacao.lerNif(sc, "NIF do cliente:");
        Cliente cliente = clinica.procurarClientePorNif(nifCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        System.out.println();
        Exibir.mostrarAnimaisDoCliente(cliente);
        int idAnimal = Leitor.lerInteiroMinimo(sc, "Id do animal:", 1);
        Animal animal = clinica.procurarAnimalPorId(idAnimal);
        if (animal == null) {
            System.out.println("Animal nao encontrado.");
            return;
        }
        if (!animal.isAtivo()) {
            System.out.println("Animal inativo.");
            return;
        }
        if (!clienteTemAnimal(cliente, animal.getId())) {
            System.out.println("Animal nao pertence ao cliente.");
            return;
        }
        String resposta = Leitor.lerSimNao(sc, "Com deslocacao? (s/n):");
        boolean comDeslocacao = resposta.equals("s");
        double distanciaKm = 0.0;
        if (comDeslocacao) {
            distanciaKm = Leitor.lerDoubleEntre(sc, "Distancia em km:", 0.0, 1000.0);
        }
        if (!Disponibilidade.veterinarioDisponivel(clinica, veterinario, data, hora, tipo, comDeslocacao, distanciaKm)) {
            System.out.println("Veterinario sem disponibilidade.");
            return;
        }
        Intervencao intervencao = new Intervencao(clinica.proximoIdIntervencao(), tipo, data, hora, veterinario, animal, cliente, comDeslocacao, distanciaKm);
        clinica.getIntervencoes().add(intervencao);
        System.out.println();
        System.out.println("Intervencao agendada com sucesso!");
        System.out.println("  Id: " + intervencao.getId());
        System.out.println("  Custo: " + intervencao.calcularCusto());
    }

    public void inserirClienteEAnimal() {
        System.out.println();
        System.out.println("--- Inserir cliente e animal ---");
        String nif = LeitorIdentificacao.lerNif(sc, "NIF do cliente:");
        if (clinica.procurarClientePorNif(nif) != null) {
            System.out.println("Cliente ja existe.");
            return;
        }
        String nome = Leitor.lerTexto(sc, "Nome:");
        String rua = Leitor.lerTexto(sc, "Rua:");
        int numero = Leitor.lerInteiroMinimo(sc, "Numero:", 1);
        String codigoPostal = LeitorIdentificacao.lerCodigoPostal(sc);
        String localidade = Leitor.lerTexto(sc, "Localidade:");
        Contacto contacto = LeitorContacto.lerContacto(sc);
        Cliente cliente = new Cliente(nif, nome, new Morada(rua, numero, codigoPostal, localidade), contacto, true);
        clinica.getClientes().add(cliente);
        int total = Leitor.lerInteiroMinimo(sc, "Numero de animais:", 1);
        for (int i = 0; i < total; i++) {
            Animal animal = lerAnimalNovo();
            clinica.getAnimais().add(animal);
            cliente.addAnimal(animal);
        }
        System.out.println("Cliente e animais inseridos.");
    }

    public void inserirAnimalEmCliente() {
        System.out.println();
        System.out.println("--- Inserir animal em cliente ---");
        Exibir.mostrarClientesDisponiveis(clinica);
        String nif = LeitorIdentificacao.lerNif(sc, "NIF do cliente:");
        Cliente cliente = clinica.procurarClientePorNif(nif);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        Animal animal = lerAnimalNovo();
        clinica.getAnimais().add(animal);
        cliente.addAnimal(animal);
        System.out.println();
        System.out.println("Animal inserido com sucesso! (id: " + animal.getId() + ")");
        String resposta = Leitor.lerSimNao(sc, "Deseja agendar intervencao agora? (s/n):");
        if (resposta.equals("s")) {
            agendarIntervencao();
        }
    }

    public void desativarAnimal() {
        System.out.println();
        System.out.println("--- Desativar animal ---");
        Exibir.mostrarClientesDisponiveis(clinica);
        String nif = LeitorIdentificacao.lerNif(sc, "NIF do cliente:");
        Cliente cliente = clinica.procurarClientePorNif(nif);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        System.out.println();
        Exibir.mostrarAnimaisDoCliente(cliente);
        int idAnimal = Leitor.lerInteiroMinimo(sc, "Id do animal:", 1);
        Animal animal = clinica.procurarAnimalPorId(idAnimal);
        if (animal == null) {
            System.out.println("Animal nao encontrado.");
            return;
        }
        if (!clienteTemAnimal(cliente, animal.getId())) {
            System.out.println("Animal nao pertence ao cliente.");
            return;
        }
        animal.setAtivo(false);
        System.out.println("Animal desativado.");
        String resposta = Leitor.lerSimNao(sc, "Deseja agendar nova intervencao? (s/n):");
        if (resposta.equals("s")) {
            agendarIntervencao();
        }
    }

    public void emitirRecibo() {
        System.out.println();
        System.out.println("--- Emitir recibo ---");
        Exibir.mostrarClientesDisponiveis(clinica);
        String nif = LeitorIdentificacao.lerNif(sc, "NIF do cliente:");
        Cliente cliente = clinica.procurarClientePorNif(nif);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        ArrayList<Intervencao> doCliente = new ArrayList<Intervencao>();
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < intervencoes.size(); i++) {
            if (intervencoes.get(i).getCliente().getNif().equals(nif)) {
                doCliente.add(intervencoes.get(i));
            }
        }
        if (doCliente.size() == 0) {
            System.out.println("Cliente sem intervencoes.");
            return;
        }
        System.out.println();
        System.out.println("Intervencoes do cliente:");
        for (int i = 0; i < doCliente.size(); i++) {
            Intervencao in = doCliente.get(i);
            System.out.println("  Id: " + in.getId() + " - " + in.getTipo() + " | " + in.getData() + " " + in.getHora() + " | animal id " + in.getAnimal().getId() + " (" + in.getAnimal().getNome() + ")");
        }
        int total = Leitor.lerInteiroMinimo(sc, "Numero de intervencoes no recibo:", 1);
        while (total > doCliente.size()) {
            System.out.println("Valor superior ao numero de intervencoes do cliente (" + doCliente.size() + "). Tente novamente.");
            total = Leitor.lerInteiroMinimo(sc, "Numero de intervencoes no recibo:", 1);
        }
        ArrayList<Intervencao> incluidas = new ArrayList<Intervencao>();
        for (int i = 0; i < total; i++) {
            int id = Leitor.lerInteiroMinimo(sc, "Id da intervencao " + (i + 1) + ":", 1);
            Intervencao intervencao = clinica.procurarIntervencaoPorId(id);
            if (intervencao == null) {
                System.out.println("Intervencao nao encontrada.");
                return;
            }
            if (!intervencao.getCliente().getNif().equals(nif)) {
                System.out.println("Intervencao nao pertence ao cliente.");
                return;
            }
            boolean jaIncluida = false;
            for (int k = 0; k < incluidas.size(); k++) {
                if (incluidas.get(k).getId() == id) {
                    jaIncluida = true;
                    break;
                }
            }
            if (jaIncluida) {
                System.out.println("Intervencao ja incluida no recibo.");
                return;
            }
            incluidas.add(intervencao);
        }
        double totalSemIva = 0.0;
        for (int i = 0; i < incluidas.size(); i++) {
            totalSemIva = totalSemIva + incluidas.get(i).calcularCusto();
        }
        double totalComIva = totalSemIva * 1.20;
        System.out.println();
        System.out.println("========================================");
        System.out.println("               RECIBO");
        System.out.println("========================================");
        System.out.println("  Cliente:     " + cliente.getNome());
        System.out.println("  NIF cliente: " + cliente.getNif());
        System.out.println("----------------------------------------");
        for (int i = 0; i < incluidas.size(); i++) {
            Intervencao in = incluidas.get(i);
            System.out.println("  Veterinario: " + in.getVeterinario().getNome());
            System.out.println("  Id animal:   " + in.getAnimal().getId() + " (" + in.getAnimal().getNome() + ")");
            System.out.println("  Intervencao: " + in.getTipo() + " - custo: " + in.calcularCusto());
            System.out.println("  --");
        }
        System.out.println("  Total sem IVA: " + totalSemIva);
        System.out.println("  Total com IVA: " + totalComIva);
        System.out.println("========================================");
    }

    private Animal lerAnimalNovo() {
        String nome = Leitor.lerTexto(sc, "Nome do animal:");
        String especie = Leitor.lerTexto(sc, "Especie:");
        String genero = Leitor.lerTexto(sc, "Genero:");
        double peso = Leitor.lerDoubleEntre(sc, "Peso:", 0.1, 1000.0);
        return new Animal(clinica.proximoIdAnimal(), nome, especie, genero, peso, true);
    }

    private boolean clienteTemAnimal(Cliente cliente, int idAnimal) {
        for (int i = 0; i < cliente.getAnimais().size(); i++) {
            if (cliente.getAnimais().get(i).getId() == idAnimal) {
                return true;
            }
        }
        return false;
    }
}
