package projetoFinal.modelo;

import java.io.Serializable;
import java.util.ArrayList;

// cliente da clinica, tem uma morada, contacto e uma lista de animais
public class Cliente implements Serializable {
    private String nif;
    private String nome;
    private Morada morada;
    private Contacto contacto;
    private boolean ativo;
    private ArrayList<Animal> animais;

    public Cliente(String nif, String nome, Morada morada, Contacto contacto, boolean ativo) {
        this.nif = nif;
        this.nome = nome;
        this.morada = morada;
        this.contacto = contacto;
        this.ativo = ativo;
        this.animais = new ArrayList<Animal>();
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Morada getMorada() {
        return morada;
    }

    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }

    public void addAnimal(Animal animal) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animal.getId()) {
                return;
            }
        }
        animais.add(animal);
    }

    public void removeAnimal(Animal animal) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animal.getId()) {
                animais.remove(i);
                break;
            }
        }
    }

    public String toString() {
        return "Cliente nif: " + nif + ", nome: " + nome + ", morada: " + morada + ", contacto: [" + contacto + "], ativo: " + ativo;
    }
}
