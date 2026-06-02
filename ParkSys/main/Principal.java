package ParkSys.main;

import ParkSys.services.GerenciadorEstacionamento;

public class Principal {
    public static void main(String[] args) {

        GerenciadorEstacionamento gerenciador =
                GerenciadorEstacionamento.getInstance();

        System.out.println(
                gerenciador.getVagas().size()
        );
    }
}
