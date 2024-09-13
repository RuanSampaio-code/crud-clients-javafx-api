package com.ruan.cep_service;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterPfView extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criando os controles da interface
        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();

        Label cpfLabel = new Label("CPF:");
        TextField cpfField = new TextField();

        Label cepLabel = new Label("CEP:");
        TextField cepField = new TextField();
        Button searchCepButton = new Button("Buscar Endereço");

        Label addressLabel = new Label("Endereço:");
        TextField addressField = new TextField();
        addressField.setEditable(false);

        Button saveButton = new Button("Salvar");

        // Criando o GridPane e adicionando os controles
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        gridPane.add(cpfLabel, 0, 1);
        gridPane.add(cpfField, 1, 1);

        gridPane.add(cepLabel, 0, 2);
        gridPane.add(cepField, 1, 2);
        gridPane.add(searchCepButton, 2, 2);

        gridPane.add(addressLabel, 0, 3);
        gridPane.add(addressField, 1, 3, 2, 1);

        gridPane.add(saveButton, 1, 4);

        // Definindo a cena e a janela
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setTitle("Cadastro Cliente Pessoa Física");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Adicionando funcionalidades aos botões
        searchCepButton.setOnAction(event -> {
            String cep = cepField.getText();
            // Aqui você deve chamar a API ViaCEP para buscar o endereço
            // e atualizar o campo addressField com o endereço obtido
            // Exemplo fictício:
            addressField.setText("Rua Exemplo, 123, Bairro Exemplo, Cidade Exemplo");
        });

        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            String cpf = cpfField.getText();
            String address = addressField.getText();

            // Aqui você deve implementar a lógica para salvar os dados do cliente PF
            System.out.println("Nome: " + name);
            System.out.println("CPF: " + cpf);
            System.out.println("Endereço: " + address);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}