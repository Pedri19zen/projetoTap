package projetoFinal.regras;

import java.util.ArrayList;
import projetoFinal.modelo.Clinica;
import projetoFinal.modelo.Intervencao;
import projetoFinal.modelo.Veterinario;

// regras de duracao de cada intervencao e verificacao de disponibilidade do veterinario
public class Disponibilidade {

    // tabela de duracoes em horas
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

    // converte "HH:MM" para minutos desde a meia-noite, devolve -1 se invalido
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

    // verifica se o vet pode aceitar a nova intervencao: nao pode passar 8h de trabalho nesse dia
    // e o novo horario nao pode sobrepor a um ja existente
    public static boolean veterinarioDisponivel(Clinica clinica, Veterinario veterinario, String data, String hora, String tipo, boolean comDeslocacao, double distanciaKm) {
        double total = duracaoIntervencao(tipo);
        if (comDeslocacao) {
            // tempo de viagem (ida e volta) a 60 km/h -> distancia*2/60 horas
            total = total + (distanciaKm * 2.0) / 60.0;
        }
        int inicioNovo = horaParaMinutos(hora);
        int fimNovo = inicioNovo + (int) (duracaoIntervencao(tipo) * 60.0);
        ArrayList<Intervencao> intervencoes = clinica.getIntervencoes();
        // soma as duracoes das outras intervencoes do mesmo vet no mesmo dia
        for (int i = 0; i < intervencoes.size(); i++) {
            Intervencao intervencao = intervencoes.get(i);
            if (intervencao.getVeterinario().getNif().equals(veterinario.getNif()) && intervencao.getData().equals(data)) {
                total = total + duracaoIntervencao(intervencao.getTipo());
                if (intervencao.isComDeslocacao()) {
                    total = total + (intervencao.getDistanciaKm() * 2.0) / 60.0;
                }
                int inicioExistente = horaParaMinutos(intervencao.getHora());
                int fimExistente = inicioExistente + (int) (duracaoIntervencao(intervencao.getTipo()) * 60.0);
                // teste de sobreposicao de intervalos
                if (inicioExistente < fimNovo && inicioNovo < fimExistente) {
                    return false;
                }
            }
        }
        return total <= 8.0;
    }
}
