package projetoFinal;

import java.util.Scanner;
import projetoFinal.menus.MenuPrincipal;

// ponto de entrada do programa
public class Main {
    public static void main(String[] args) {
        // usamos um unico Scanner partilhado por todos os menus
        Scanner sc = new Scanner(System.in);
        MenuPrincipal menu = new MenuPrincipal(sc);
        menu.executar();
        sc.close();
    }
}
