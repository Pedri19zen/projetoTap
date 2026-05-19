package projetoFinal.modelo;

import java.io.Serializable;

// veterinario da clinica, identificado pelo NIF
public class Veterinario implements Serializable {
    private String nif;
    private String nome;
    private String idOrdem;
    private Contacto contacto;

    public Veterinario(String nif, String nome, String idOrdem, Contacto contacto) {
        this.nif = nif;
        this.nome = nome;
        this.idOrdem = idOrdem;
        this.contacto = contacto;
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

    public String getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(String idOrdem) {
        this.idOrdem = idOrdem;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public String toString() {
        return "Veterinario nif: " + nif + ", nome: " + nome + ", id ordem: " + idOrdem + ", contacto: [" + contacto + "]";
    }
}
