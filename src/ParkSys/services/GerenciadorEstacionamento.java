package ParkSys.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import ParkSys.entities.*;
import ParkSys.enums.StatusVaga;
import ParkSys.enums.TipoVeiculo;
import ParkSys.exceptions.VeiculoNaoEncontradoException;
import ParkSys.observer.EstacionamentoObserver;

import java.time.Duration;
import java.time.LocalDateTime;

public class GerenciadorEstacionamento {
    private static GerenciadorEstacionamento instance;

    private HashMap<String, Vaga> vagas;
    private ArrayList<Registro> registros;
    private LinkedList<Mensalista> mensalistas;
    private ArrayList<EstacionamentoObserver> observers;

    private GerenciadorEstacionamento() {

        vagas = new HashMap<>();
        registros = new ArrayList<>();
        mensalistas = new LinkedList<>();
        observers = new ArrayList<>();

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

// synchronized evita race condition. Sem essa proteção, duas threads poderiam  ocupar a mesma vaga simultaneamente.
public synchronized void registrarEntrada(String placa, TipoVeiculo tipo) {

    

    Veiculo veiculo = new Veiculo(placa, tipo);

    for (Vaga vaga : vagas.values()) {

        if (vaga.getStatus() == StatusVaga.LIVRE) {

            vaga.setStatus(StatusVaga.OCUPADA);

           Registro registro = new Registro(
        veiculo,
        vaga.getId()
);
         registro.setThreadOrigem(
                Thread.currentThread().getName()
        );

        registros.add(registro);

            System.out.println("Veículo estacionado na vaga: "
                    + vaga.getId());
   notificarObservers(
        "Entrada do veículo " + placa +
        " na vaga " + vaga.getId());

            return;
        }
       

    }
  
    
}
// synchronized evita alterações simultâneas na lista de registros e no mapa de vagas.
public synchronized void registrarSaida(String placa) throws VeiculoNaoEncontradoException  {
    

    for (Registro registro : registros) {

        if (registro.getVeiculo().getPlaca().equalsIgnoreCase(placa)
                && registro.getDataSaida() == null) {

            registro.setDataSaida(LocalDateTime.now());

            long horas = Duration.between(
                    registro.getDataEntrada(),
                    registro.getDataSaida()
            ).toHours();

            if (horas == 0) {
                horas = 1;
            }

            double valor = horas *
                    registro.getVeiculo()
                            .getTipo()
                            .getTarifaHora();

            registro.setValorPago(valor);

            String idVaga = registro.getIdVaga();

            Vaga vaga = vagas.get(idVaga);

            if (vaga != null) {
                vaga.setStatus(StatusVaga.LIVRE);
            }

            System.out.println("Saída registrada!");
            System.out.println("Vaga liberada: " + idVaga);
            System.out.println("Valor pago: R$ " + valor);

            notificarObservers(
        "Saída do veículo " + placa +
        " da vaga " + idVaga
);

            return;
        }
    }

throw new VeiculoNaoEncontradoException(
        "Veículo não encontrado."
);
   
}
public void cadastrarMensalista(Mensalista mensalista) {
    mensalistas.add(mensalista);
}
public Mensalista buscarMensalista(String placa) {

    for (Mensalista mensalista : mensalistas) {

        if (mensalista.getPlaca()
                .equalsIgnoreCase(placa)) {

            return mensalista;
        }
    }

    return null;
}
public boolean removerMensalista(String placa) {

    Mensalista mensalista =
            buscarMensalista(placa);

    if (mensalista != null) {

        mensalistas.remove(mensalista);

        return true;
    }

    return false;
}
public void adicionarObserver(
        EstacionamentoObserver observer) {

    observers.add(observer);
}
private void notificarObservers(
        String mensagem) {

    for (EstacionamentoObserver observer : observers) {

        observer.atualizar(mensagem);
    }
}

}




