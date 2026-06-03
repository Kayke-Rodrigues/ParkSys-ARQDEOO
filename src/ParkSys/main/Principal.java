package ParkSys.main;

import ParkSys.entities.Mensalista;
import ParkSys.entities.Registro;
import ParkSys.enums.TipoVeiculo;
import ParkSys.exceptions.VeiculoNaoEncontradoException;
import ParkSys.observer.PainelMonitor;
import ParkSys.services.EntradaRunnable;
import ParkSys.services.GerenciadorArquivo;
import ParkSys.services.GerenciadorEstacionamento;

public class Principal {
    public static void main(String[] args) {
        //testes

        GerenciadorEstacionamento gerenciador =
                GerenciadorEstacionamento.getInstance();

        System.out.println(
                gerenciador.getVagas().size()
        );
                
     gerenciador.registrarEntrada("ABC1234", TipoVeiculo.CARRO);
     try {
    gerenciador.registrarSaida("ABC1234");
} catch (VeiculoNaoEncontradoException e) {
    System.out.println(e.getMessage());
}
GerenciadorArquivo.salvar(
        gerenciador.getRegistros(),
        "registros.dat"
);
GerenciadorArquivo.exportarRelatorio(
        gerenciador.getRegistros(),
        "relatorio.txt"
);
Mensalista m1 =
        new Mensalista(
                "ABC1234",
                "Leonardo","A01"
        );

gerenciador.cadastrarMensalista(m1);

Mensalista encontrado =
        gerenciador.buscarMensalista("ABC1234");

if (encontrado != null) {
    System.out.println("Mensalista encontrado!");
}
PainelMonitor monitor =
        new PainelMonitor();

gerenciador.adicionarObserver(monitor);

gerenciador.registrarEntrada("ABC1234", TipoVeiculo.CARRO);


Thread t1 = new Thread(
        new EntradaRunnable(
                "AAA1111",
                TipoVeiculo.CARRO,
                gerenciador
        ),
        "Entrada-1"
);

Thread t2 = new Thread(
        new EntradaRunnable(
                "BBB2222",
                TipoVeiculo.MOTO,
                gerenciador
        ),
        "Entrada-2"
);

Thread t3 = new Thread(
        new EntradaRunnable(
                "CCC3333",
                TipoVeiculo.SUV,
                gerenciador
        ),
        "Entrada-3"
);

Thread t4 = new Thread(
        new EntradaRunnable(
                "DDD4444",
                TipoVeiculo.CAMINHAO,
                gerenciador
        ),
        "Entrada-4"
);
t1.start();
t2.start();
t3.start();
t4.start();

try {

    t1.join();
    t2.join();
    t3.join();
    t4.join();

} catch (InterruptedException e) {

    e.printStackTrace();
}
for (Registro r : gerenciador.getRegistros()) {

    System.out.println(
            r.getVeiculo().getPlaca()
            + " -> "
            + r.getThreadOrigem()
    );
}
}
}
