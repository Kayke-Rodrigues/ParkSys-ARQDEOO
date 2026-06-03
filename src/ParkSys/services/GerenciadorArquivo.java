package ParkSys.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import ParkSys.entities.Registro;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class GerenciadorArquivo {
     public static void salvar(Object objeto, String nomeArquivo) {

        try {

            FileOutputStream fos =
                    new FileOutputStream(nomeArquivo);

            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);

            oos.writeObject(objeto);

            oos.close();

            System.out.println("Dados salvos.");

        } catch (IOException e) {

            System.out.println("Erro ao salvar."+ e.getMessage());
        }
    }
    public static Object carregar(String nomeArquivo) {

    try {

        FileInputStream fis =
                new FileInputStream(nomeArquivo);

        ObjectInputStream ois =
                new ObjectInputStream(fis);

        Object objeto = ois.readObject();

        ois.close();

        return objeto;

    } catch (Exception e) {

        System.out.println("Erro ao carregar."+ e.getMessage());

        return null;
    }
}
public static void exportarRelatorio(ArrayList<Registro> registros,
                                     String nomeArquivo) {

    try {

        PrintWriter writer = new PrintWriter(nomeArquivo);

        for (Registro registro : registros) {

            writer.println("Placa: "
                    + registro.getVeiculo().getPlaca());

            writer.println("Tipo: "
                    + registro.getVeiculo().getTipo());

            writer.println("Entrada: "
                    + registro.getDataEntrada());

            writer.println("Saída: "
                    + registro.getDataSaida());

            writer.println("Valor Pago: R$ "
                    + registro.getValorPago());

            writer.println("---------------------");
        }

        writer.close();

        System.out.println("Relatório exportado.");

    } catch (Exception e) {

        System.out.println("Erro ao exportar relatório.");
    }
}


    
}
