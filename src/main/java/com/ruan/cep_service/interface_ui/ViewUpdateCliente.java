package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.service.ClienteService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewUpdateCliente {
    @Autowired
    private ClienteService clienteService;

    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Campos para entrada de dados do cliente
        Label idLabel = new Label("ID do Cliente:");
        TextField idField = new TextField();

        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField();

        Label cpfcnpjLabel = new Label("CPF/CNPJ:");
        TextField cpfcnpjField = new TextField();

        Label tipoLabel = new Label("Tipo:");
        TextField tipoField = new TextField();

        Label enderecoLabel = new Label("Endereço:");
        TextField enderecoField = new TextField();

        Button updateButton = new Button("Atualizar Cliente");

        // Ação de atualização ao clicar no botão
        updateButton.setOnAction(event -> {
            Long id = Long.parseLong(idField.getText());
            Cliente clienteAtualizado = new Cliente();
            clienteAtualizado.setNome(nomeField.getText());
            clienteAtualizado.setEmail(emailField.getText());
            clienteAtualizado.setTelefone(telefoneField.getText());
            clienteAtualizado.setCpfcnpj(cpfcnpjField.getText());
            // Você pode ajustar a lógica de tipo e endereço conforme necessário
            clienteService.atualizarCliente(id, clienteAtualizado);
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nomeLabel, 0, 1);
        gridPane.add(nomeField, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(telefoneLabel, 0, 3);
        gridPane.add(telefoneField, 1, 3);
        gridPane.add(cpfcnpjLabel, 0, 4);
        gridPane.add(cpfcnpjField, 1, 4);
        gridPane.add(tipoLabel, 0, 5);
        gridPane.add(tipoField, 1, 5);
        gridPane.add(enderecoLabel, 0, 6);
        gridPane.add(enderecoField, 1, 6);
        gridPane.add(updateButton, 1, 7);

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setTitle("Atualizar Cliente");
        stage.setScene(scene);
        stage.show();
    }

}
