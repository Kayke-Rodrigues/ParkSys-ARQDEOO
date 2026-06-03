package ParkSys.entities;

import java.io.Serializable;

public class Mensalista implements Serializable {
     private static final long serialVersionUID = 1L;

    private String nome;
    private String placa;
    private String vagaReservada;

    public Mensalista(String nome, String placa, String vagaReservada) {
        this.nome = nome;
        this.placa = placa;
        this.vagaReservada = vagaReservada;
    }

    public String getNome() {
        return nome;
    }

    public String getPlaca() {
        return placa;
    }

    public String getVagaReservada() {
        return vagaReservada;
    }
}
