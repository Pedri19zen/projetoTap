package projetoFinal.regras;

import java.util.ArrayList;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Veterinario;

public class Disponibilidade {

    public static double duracaoIntervencao(String tipo) {
        if (tipo.equals("cirurgia")) {
            return 2.0;
        }
        if (tipo.equals("consulta")) {
            return 0.5;
        }
        if (tipo.equals("vacina")) {
            return 0.5;
        }
        return 0.0;
    }

    public static int horaParaMinutos(String hora) {
        try {
            String[] p = hora.split(":");
            if (p.length != 2) {
                return -1;
            }
            int h = Integer.parseInt(p[0]);
            int m = Integer.parseInt(p[1]);
            if (h < 0 || h > 23 || m < 0 || m > 59) {
                return -1;
            }
            return h * 60 + m;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean veterinarioDisponivel(Clinica clinica, Veterinario veterinario, String data, String hora, String tipo, boolean comDeslocacao, double distanciaKm) {
        double total = duracaoIntervencao(tipo);
        if (comDeslocacao) {
            total = total + (distanciaKm * 2.0) / 60.0;
        }
        int inicioNovo = horaParaMinutos(hora);
        int fimNovo = inicioNovo + (int) (duracaoIntervencao(tipo) * 60.0);
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        for (int i = 0; i < intervencoes.size(); i++) {
            Intervencao intervencao = intervencoes.get(i);
            if (intervencao.getVeterinario().getNif().equals(veterinario.getNif()) && intervencao.getData().equals(data)) {
                total = total + duracaoIntervencao(intervencao.getTipo());
                if (intervencao.isComDeslocacao()) {
                    total = total + (intervencao.getDistanciaKm() * 2.0) / 60.0;
                }
                int inicioExistente = horaParaMinutos(intervencao.getHora());
                int fimExistente = inicioExistente + (int) (duracaoIntervencao(intervencao.getTipo()) * 60.0);
                if (inicioExistente < fimNovo && inicioNovo < fimExistente) {
                    return false;
                }
            }
        }
        return total <= 8.0;
    }
}
