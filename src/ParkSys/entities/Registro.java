package ParkSys.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Registro implements Serializable, Comparable<Registro> {

    private static final long serialVersionUID = 1L;

    private Veiculo veiculo;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private double valorPago;
    private String idVaga;

    // Lista de vagas extras ocupadas por SUV (1 extra) e Caminhão (2 extras)
    private List<String> vagasExtras;

    
    private transient String threadOrigem;

    public Registro(Veiculo veiculo, String idVaga) {
        this.veiculo = veiculo;
        this.idVaga = idVaga;
        this.dataEntrada = LocalDateTime.now();
    }

    @Override
    public int compareTo(Registro outro) {
        return this.dataEntrada.compareTo(outro.dataEntrada);
    }

    public String getIdVaga() {
        return idVaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getThreadOrigem() {
        return threadOrigem;
    }

    public void setThreadOrigem(String threadOrigem) {
        this.threadOrigem = threadOrigem;
    }

    public List<String> getVagasExtras() {
        return vagasExtras;
    }

    public void setVagasExtras(List<String> vagasExtras) {
        this.vagasExtras = vagasExtras;
    }
}