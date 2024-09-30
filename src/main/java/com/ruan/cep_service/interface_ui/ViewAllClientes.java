package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.service.ClienteService;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewAllClientes {

    @Autowired
    private ClienteService clienteService;

    private TableView<Cliente> tabelaClientes;

    public void start(Stage stageTabelaDeClientes) {
        List<Cliente> listaDeClientes = clienteService.findAllClientes();

        for (Cliente cliente : listaDeClientes) {
            System.out.println(cliente);  // Assumindo que a classe Cliente tem um método toString() sobrescrito
        }



        // Criar um novo palco para exibir a tabela de clientes
        stageTabelaDeClientes.setTitle("Lista de Clientes");

        // Cria uma TableView para exibir os clientes
        tabelaClientes = new TableView<>();

        //logica das clientes pra ca

        // Criar o layout da cena
        StackPane root = new StackPane();
        root.getChildren().add(tabelaClientes);

        // Criar a cena
        Scene scene = new Scene(root, 600, 200);

        // Configurar a cena no novo palco e exibi-lo
        stageTabelaDeClientes.setScene(scene);
        stageTabelaDeClientes.show();

    }
}
