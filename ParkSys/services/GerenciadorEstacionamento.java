package ParkSys.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import ParkSys.entities.*;

public class GerenciadorEstacionamento {
    private static GerenciadorEstacionamento instance;

    private HashMap<String, Vaga> vagas;
    private ArrayList<Registro> registros;
    private LinkedList<Mensalista> mensalistas;

    private GerenciadorEstacionamento() {

        vagas = new HashMap<>();
        registros = new ArrayList<>();
        mensalistas = new LinkedList<>();

        inicializarVagas();
    }

    public static GerenciadorEstacionamento getInstance() {

        if(instance == null) {
            instance = new GerenciadorEstacionamento();
        }

        return instance;
    }

    private void inicializarVagas() {

        for(int i = 1; i <= 15; i++) {

            String vagaA = String.format("A%02d", i);
            String vagaB = String.format("B%02d", i);

            vagas.put(vagaA, new Vaga(vagaA));
            vagas.put(vagaB, new Vaga(vagaB));
        }
    }
    public HashMap<String, Vaga> getVagas() {
    return vagas;
}

public ArrayList<Registro> getRegistros() {
    return registros;
}

public LinkedList<Mensalista> getMensalistas() {
    return mensalistas;
}
}
