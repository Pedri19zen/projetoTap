package projetoFinal.exibicao;

import java.util.ArrayList;
import projetoFinal.modelo.Animal;
import projetoFinal.modelo.Cliente;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Veterinario;

public class Exibir {

    public static void mostrarVeterinariosDisponiveis(Clinica clinica) {
        System.out.println("Veterinarios disponiveis:");
        ArrayList<Veterinario> veterinarios = clinica.getVeterinarios();
        for (int i = 0; i < veterinarios.size(); i++) {
            Veterinario v = veterinarios.get(i);
            System.out.println("  NIF: " + v.getNif() + " - " + v.getNome());
        }
        if (veterinarios.size() == 0) {
            System.out.println("  (nenhum)");
        }
    }

    public static void mostrarClientesDisponiveis(Clinica clinica) {
        System.out.println("Clientes disponiveis:");
        ArrayList<Cliente> clientes = clinica.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.println("  NIF: " + c.getNif() + " - " + c.getNome());
        }
        if (clientes.size() == 0) {
            System.out.println("  (nenhum)");
        }
    }

    public static void mostrarAnimaisDoCliente(Cliente cliente) {
        System.out.println("Animais de " + cliente.getNome() + ":");
        for (int i = 0; i < cliente.getAnimais().size(); i++) {
            Animal a = cliente.getAnimais().get(i);
            String estado = a.isAtivo() ? "ativo" : "inativo";
            System.out.println("  Id: " + a.getId() + " - " + a.getNome() + " (" + a.getEspecie() + ", " + estado + ")");
        }
        if (cliente.getAnimais().size() == 0) {
            System.out.println("  (nenhum)");
        }
    }
}
