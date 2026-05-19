package projetoFinal.modelo;

import java.io.Serializable;

public class Contacto implements Serializable {
    private String email;
    private String telefone;
    private String metodoPreferido;

    public Contacto(String email, String telefone, String metodoPreferido) {
        this.email = email;
        this.telefone = telefone;
        this.metodoPreferido = metodoPreferido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMetodoPreferido() {
        return metodoPreferido;
    }

    public void setMetodoPreferido(String metodoPreferido) {
        this.metodoPreferido = metodoPreferido;
    }

    public String toString() {
        return "email: " + email + ", telefone: " + telefone + ", preferido: " + metodoPreferido;
    }
}
