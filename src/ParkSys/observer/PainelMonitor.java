package ParkSys.observer;


public class PainelMonitor implements EstacionamentoObserver {
    @Override
    public void atualizar(String mensagem) {

        System.out.println("[MONITOR] " + mensagem);
}
}
