package ParkSys.services;

import ParkSys.enums.TipoVeiculo;

public class EntradaRunnable implements Runnable {

    private String placa;
    private TipoVeiculo tipo;
    private GerenciadorEstacionamento gerenciador;

    public EntradaRunnable(
            String placa,
            TipoVeiculo tipo,
            GerenciadorEstacionamento gerenciador) {

        this.placa = placa;
        this.tipo = tipo;
        this.gerenciador = gerenciador;
    }

    @Override
    public void run() {

        try {

            Thread.sleep(200);

            gerenciador.registrarEntrada(
                    placa,
                    tipo
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}