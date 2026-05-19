package projetoFinal.modelo;

import java.io.Serializable;

public class Animal implements Serializable {
    private int id;
    private String nome;
    private String especie;
    private String genero;
    private double peso;
    private boolean ativo;

    public Animal(int id, String nome, String especie, String genero, double peso, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.genero = genero;
        this.peso = peso;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String toString() {
        return "Animal id: " + id + ", nome: " + nome + ", especie: " + especie + ", genero: " + genero + ", peso: " + peso + ", ativo: " + ativo;
    }
}
