package projetoFinal;

import java.util.Scanner;
import projetoFinal.menus.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuPrincipal menu = new MenuPrincipal(sc);
        menu.executar();
        sc.close();
    }
}
