package com.ruan.cep_service;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criando botões para cada operação
        Button registerPfButton = new Button("Cadastro Cliente PF");
        Button registerPjButton = new Button("Cadastro Cliente PJ");
        Button viewAllButton = new Button("Ver Todos os Clientes");
        Button updateClientButton = new Button("Alterar Cliente");
        Button deleteClientButton = new Button("Deletar Cliente");

        // Adicionando ação aos botões
        registerPfButton.setOnAction(event -> showRegisterPfView());
//        registerPjButton.setOnAction(event -> showRegisterPjView());
//        viewAllButton.setOnAction(event -> showViewAllClientsView());
//        updateClientButton.setOnAction(event -> showUpdateClientView());
//        deleteClientButton.setOnAction(event -> showDeleteClientView());

        // Criando o layout (VBox) e adicionando os botões
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(registerPfButton, registerPjButton, viewAllButton, updateClientButton, deleteClientButton);

        // Criando a cena e definindo o tamanho da janela
        Scene scene = new Scene(vbox, 400, 350);
        primaryStage.setTitle("Gerenciamento de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showRegisterPfView() {
        new com.ruan.cep_service.RegisterPfView().start(new Stage());
    }

//    private void showRegisterPjView() {
//        new RegisterPjView().start(new Stage());
//    }
//
//    private void showViewAllClientsView() {
//        new ViewAllClientsView().start(new Stage());
//    }
//
//    private void showUpdateClientView() {
//        new UpdateClientView().start(new Stage());
//    }
//
//    private void showDeleteClientView() {
//        new DeleteClientView().start(new Stage());
//    }

    public static void main(String[] args) {
        launch(args);
    }

}