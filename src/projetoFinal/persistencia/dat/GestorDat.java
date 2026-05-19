package projetoFinal.persistencia.dat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import projetoFinal.modelo.Clinica;

// gestor da persistencia binaria: grava e le o objeto Clinica num ficheiro .dat
// usa Serializable, e mais rapido mas o ficheiro nao e legivel para humanos
public class GestorDat {

    public static void gravar(Clinica clinica) {
        try {
            clinica.garantirPastaDados();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(clinica.caminho("clinica.dat")));
            oos.writeObject(clinica);
            oos.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar dat.");
        }
    }

    public static boolean ler(Clinica clinica) {
        File f = new File(clinica.caminho("clinica.dat"));
        if (!f.exists()) {
            return false;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(clinica.caminho("clinica.dat")));
            Clinica c = (Clinica) ois.readObject();
            ois.close();
            clinica.setVeterinarios(c.getVeterinarios());
            clinica.setClientes(c.getClientes());
            clinica.setAnimais(c.getAnimais());
            clinica.setIntervencoes(c.getIntervencoes());
            clinica.setDataHoje(c.getDataHoje());
            clinica.setPastaDados(c.getPastaDados());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
