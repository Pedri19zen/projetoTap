package projetoFinal.modelo;

import java.io.Serializable;

public class Morada implements Serializable {
    private String rua;
    private int numero;
    private String codigoPostal;
    private String localidade;

    public Morada(String rua, int numero, String codigoPostal, String localidade) {
        this.rua = rua;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.localidade = localidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String toString() {
        return rua + ", " + numero + ", " + codigoPostal + " " + localidade;
    }
}
