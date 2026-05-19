package projetoFinal.modelo;

import java.io.Serializable;

// intervencao agendada/realizada na clinica: liga um vet, um animal e um cliente numa dada data/hora
public class Intervencao implements Serializable {
    private int id;
    private String tipo;
    private String data;
    private String hora;
    private Veterinario veterinario;
    private Animal animal;
    private Cliente cliente;
    private boolean comDeslocacao;
    private double distanciaKm;

    public Intervencao(int id, String tipo, String data, String hora, Veterinario veterinario, Animal animal, Cliente cliente, boolean comDeslocacao, double distanciaKm) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.hora = hora;
        this.veterinario = veterinario;
        this.animal = animal;
        this.cliente = cliente;
        this.comDeslocacao = comDeslocacao;
        this.distanciaKm = distanciaKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isComDeslocacao() {
        return comDeslocacao;
    }

    public void setComDeslocacao(boolean comDeslocacao) {
        this.comDeslocacao = comDeslocacao;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    // calcula o custo: preco por tipo, com escalao para animais com mais de 10kg
    // e soma 40 + distancia se houver deslocacao
    public double calcularCusto() {
        double custo = 0.0;
        if (animal != null) {
            if (tipo.equalsIgnoreCase("consulta")) {
                if (animal.getPeso() < 10.0) {
                    custo = 25.0;
                } else {
                    custo = 50.0;
                }
            }
            if (tipo.equalsIgnoreCase("vacina")) {
                if (animal.getPeso() < 10.0) {
                    custo = 50.0;
                } else {
                    custo = 100.0;
                }
            }
            if (tipo.equalsIgnoreCase("cirurgia")) {
                if (animal.getPeso() < 10.0) {
                    custo = 200.0;
                } else {
                    custo = 400.0;
                }
            }
        }
        if (comDeslocacao) {
            custo = custo + 40.0 + distanciaKm;
        }
        return custo;
    }

    public String toString() {
        String nomeVet = "";
        String nomeAnimal = "";
        String nomeCliente = "";
        if (veterinario != null) {
            nomeVet = veterinario.getNome();
        }
        if (animal != null) {
            nomeAnimal = animal.getNome();
        }
        if (cliente != null) {
            nomeCliente = cliente.getNome();
        }
        return "Intervencao id: " + id + ", tipo: " + tipo + ", data: " + data + ", hora: " + hora + ", veterinario: " + nomeVet + ", animal: " + nomeAnimal + ", cliente: " + nomeCliente + ", deslocacao: " + comDeslocacao + ", distancia km: " + distanciaKm + ", custo: " + calcularCusto();
    }
}
