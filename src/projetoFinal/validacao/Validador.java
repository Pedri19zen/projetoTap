package projetoFinal.validacao;

import projetoFinal.regras.Disponibilidade;

// classe com metodos estaticos para validar os varios tipos de input
public class Validador {

    // verifica se a string so tem digitos de 0 a 9
    public static boolean textoNumerico(String texto) {
        if (texto.length() == 0) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) < '0' || texto.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    // valida data no formato YYYY-MM-DD (com tracos nas posicoes 4 e 7)
    public static boolean dataValida(String data) {
        if (data.length() != 10) {
            return false;
        }
        if (data.charAt(4) != '-' || data.charAt(7) != '-') {
            return false;
        }
        String anoTexto = data.substring(0, 4);
        String mesTexto = data.substring(5, 7);
        String diaTexto = data.substring(8, 10);
        if (!textoNumerico(anoTexto) || !textoNumerico(mesTexto) || !textoNumerico(diaTexto)) {
            return false;
        }
        try {
            int ano = Integer.parseInt(anoTexto);
            int mes = Integer.parseInt(mesTexto);
            int dia = Integer.parseInt(diaTexto);
            if (ano < 1900 || ano > 2100 || mes < 1 || mes > 12 || dia < 1) {
                return false;
            }
            // descobrir o maximo de dias do mes
            int maxDia = 31;
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                maxDia = 30;
            }
            if (mes == 2) {
                // ano bissexto: divisivel por 4, mas nao por 100, ou divisivel por 400
                boolean bissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
                if (bissexto) {
                    maxDia = 29;
                } else {
                    maxDia = 28;
                }
            }
            if (dia > maxDia) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // valida hora no formato HH:MM
    public static boolean horaValida(String hora) {
        if (hora.length() != 5) {
            return false;
        }
        if (hora.charAt(2) != ':') {
            return false;
        }
        if (!textoNumerico(hora.substring(0, 2)) || !textoNumerico(hora.substring(3, 5))) {
            return false;
        }
        return Disponibilidade.horaParaMinutos(hora) >= 0;
    }

    // NIF tem que ter 9 digitos
    public static boolean nifValido(String nif) {
        return nif.length() == 9 && textoNumerico(nif);
    }

    // telefone tem 9 digitos e tem que comecar por 2 (fixo) ou 9 (movel)
    public static boolean telefoneValido(String telefone) {
        return telefone.length() == 9
            && textoNumerico(telefone)
            && (telefone.charAt(0) == '2' || telefone.charAt(0) == '9');
    }

    // codigo postal portugues: xxxx-xxx
    public static boolean codigoPostalValido(String cp) {
        try {
            return cp.length() == 8
                && cp.charAt(4) == '-'
                && textoNumerico(cp.substring(0, 4))
                && textoNumerico(cp.substring(5, 8))
                && Integer.parseInt(cp.substring(0, 4)) >= 1000;
        } catch (Exception e) {
            return false;
        }
    }

    // valida um email simples: tem um @, nao tem espacos nem ;, e tem um ponto depois do @
    public static boolean emailValido(String email) {
        if (email == null || email.length() == 0) {
            return false;
        }
        if (email.contains(";") || email.contains(" ")) {
            return false;
        }
        int at = email.indexOf('@');
        if (at <= 0 || at >= email.length() - 1) {
            return false;
        }
        // nao pode ter mais que um @
        if (email.indexOf('@', at + 1) != -1) {
            return false;
        }
        int dot = email.indexOf('.', at);
        if (dot == -1 || dot == email.length() - 1) {
            return false;
        }
        return true;
    }

    // so aceita "email" ou "telefone" como metodo preferido
    public static boolean metodoContactoValido(String metodo) {
        return metodo != null && (metodo.equals("email") || metodo.equals("telefone"));
    }
}
