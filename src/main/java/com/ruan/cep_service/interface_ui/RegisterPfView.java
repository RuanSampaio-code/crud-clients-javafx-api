package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.endereco.Endereco;
import com.ruan.cep_service.requisicaoViaCep.Requisicao;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterPfView extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criando os controles da interface
        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();

        Label cnpjLabel = new Label("CNPJ:");
        TextField cnpjField = new TextField();

        Label cepLabel = new Label("CEP:");
        TextField cepField = new TextField();
        Button searchCepButton = new Button("Buscar Endereço");

        // Campos de endereço
        Label logradouroLabel = new Label("Logradouro:");
        TextField logradouroField = new TextField();

        Label bairroLabel = new Label("Bairro:");
        TextField bairroField = new TextField();

        Label cidadeLabel = new Label("Cidade:");
        TextField cidadeField = new TextField();

        Label ufLabel = new Label("UF:");
        TextField ufField = new TextField();

        // Campos Número e Complemento - movidos para baixo
        Label numeroLabel = new Label("Número:");
        TextField numeroField = new TextField();

        Label complementoLabel = new Label("Complemento:");
        TextField complementoField = new TextField();

        Button saveButton = new Button("Salvar");

        // Criando o GridPane e adicionando os controles
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Adicionando os componentes ao GridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        gridPane.add(cnpjLabel, 0, 1);
        gridPane.add(cnpjField, 1, 1);

        gridPane.add(cepLabel, 0, 2);
        gridPane.add(cepField, 1, 2);
        gridPane.add(searchCepButton, 2, 2);

        gridPane.add(logradouroLabel, 0, 3);
        gridPane.add(logradouroField, 1, 3);

        gridPane.add(bairroLabel, 0, 4);
        gridPane.add(bairroField, 1, 4);

        // Adicionando Cidade e UF antes de Número e Complemento
        gridPane.add(cidadeLabel, 0, 5);
        gridPane.add(cidadeField, 1, 5);

        gridPane.add(ufLabel, 0, 6);
        gridPane.add(ufField, 1, 6);

        // Adicionando Número e Complemento nas últimas linhas
        gridPane.add(numeroLabel, 0, 7);
        gridPane.add(numeroField, 1, 7);

        gridPane.add(complementoLabel, 0, 8);
        gridPane.add(complementoField, 1, 8);
        gridPane.add(saveButton, 1, 9);

        // Definindo a cena e a janela
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("Cadastro Cliente Pessoa Física");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Adicionando funcionalidades aos botões
        searchCepButton.setOnAction(event -> {
            String cep = cepField.getText();

            //Chamando a requisção do CEP
            Requisicao requisicao = new Requisicao();

            try {
                //Apontando para os campos da interface
                Endereco endereco = requisicao.retornaJson(cep);
                logradouroField.setText(endereco.getLogradouro());
                bairroField.setText(endereco.getBairro());
                numeroField.setText(endereco.getNumero());
                complementoField.setText(endereco.getComplemento());
                cidadeField.setText(endereco.getCidade());
                ufField.setText(endereco.getUf());

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            String cnpj = cnpjField.getText();
            String logradouro = logradouroField.getText();
            String bairro = bairroField.getText();
            String numero = numeroField.getText();
            String complemento = complementoField.getText();
            String cidade = cidadeField.getText();
            String uf = ufField.getText();


            // Aqui você deve implementar a lógica para salvar os dados do cliente PJ
            System.out.println("Nome: " + name);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Logradouro: " + logradouro);
            System.out.println("Bairro: " + bairro);
            System.out.println("Número: " + numero);
            System.out.println("Complemento: " + complemento);
            System.out.println("Cidade: " + cidade);
            System.out.println("UF: " + uf);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}