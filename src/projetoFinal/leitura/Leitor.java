package projetoFinal.leitura;

import java.util.Scanner;

public class Leitor {

    public static String lerTexto(Scanner sc, String mensagem) {
        String valor = "";
        boolean valido = false;
        do {
            System.out.println(mensagem);
            valor = sc.nextLine().trim();
            if (valor.length() == 0) {
                System.out.println("Valor invalido. Tente novamente.");
            } else if (valor.contains(";")) {
                System.out.println("Valor nao pode conter ';'. Tente novamente.");
            } else {
                valido = true;
            }
        } while (!valido);
        return valor;
    }

    public static int lerInteiroMinimo(Scanner sc, String mensagem, int minimo) {
        int valor = minimo;
        boolean valido = false;
        do {
            System.out.println(mensagem);
            try {
                valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= minimo) {
                    valido = true;
                } else {
                    System.out.println("Valor invalido. Tente novamente.");
                }
            } catch (java.util.NoSuchElementException e) {
                throw e;
            } catch (Exception e) {
                System.out.println("Valor invalido. Tente novamente.");
            }
        } while (!valido);
        return valor;
    }

    public static double lerDoubleMinimo(Scanner sc, String mensagem, double minimo) {
        double valor = minimo;
        boolean valido = false;
        do {
            System.out.println(mensagem);
            try {
                valor = Double.parseDouble(sc.nextLine().trim());
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("Valor invalido. Tente novamente.");
                } else if (valor >= minimo) {
                    valido = true;
                } else {
                    System.out.println("Valor invalido. Tente novamente.");
                }
            } catch (java.util.NoSuchElementException e) {
                throw e;
            } catch (Exception e) {
                System.out.println("Valor invalido. Tente novamente.");
            }
        } while (!valido);
        return valor;
    }

    public static double lerDoubleEntre(Scanner sc, String mensagem, double minimo, double maximo) {
        double valor = minimo;
        boolean valido = false;
        do {
            System.out.println(mensagem);
            try {
                valor = Double.parseDouble(sc.nextLine().trim());
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("Valor invalido. Tente novamente.");
                } else if (valor >= minimo && valor <= maximo) {
                    valido = true;
                } else {
                    System.out.println("Valor invalido. Tente novamente.");
                }
            } catch (java.util.NoSuchElementException e) {
                throw e;
            } catch (Exception e) {
                System.out.println("Valor invalido. Tente novamente.");
            }
        } while (!valido);
        return valor;
    }

    public static String lerSimNao(Scanner sc, String mensagem) {
        String valor = "";
        boolean valido = false;
        do {
            System.out.println(mensagem);
            valor = sc.nextLine().trim().toLowerCase();
            if (valor.equals("s") || valor.equals("n")) {
                valido = true;
            } else {
                System.out.println("Valor invalido. Use s ou n.");
            }
        } while (!valido);
        return valor;
    }
}
