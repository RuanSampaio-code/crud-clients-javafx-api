package com.ruan.cep_service;

import com.ruan.cep_service.interface_ui.RegisterPfPjView;
import com.ruan.cep_service.service.ClienteService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApp extends Application {

    private ClienteService clienteService;

    @Override
    public void start(Stage primaryStage) {
        // Obtemos o ClienteService do contexto do Spring
        clienteService = SpringContext.getBean(ClienteService.class);

        // Criando botões para cada operação
        Button registerPfButton = new Button("Cadastro Cliente PF");
        Button registerPjButton = new Button("Cadastro Cliente PJ");
        Button viewAllButton = new Button("Ver Todos os Clientes");
        Button updateClientButton = new Button("Alterar Cliente");
        Button deleteClientButton = new Button("Deletar Cliente");

        // Adicionando ação aos botões
        registerPfButton.setOnAction(event -> showRegisterPfPjView());
        //registerPjButton.setOnAction(event -> showRegisterPjView());
        //viewAllButton.setOnAction(event -> showViewAllClientsView());
//        updateClientButton.setOnAction(event -> showUpdateClientView());
//        deleteClientButton.setOnAction(event -> showDeleteClientView());

        // Criando o layout (VBox) e adicionando os botões
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(registerPfButton, registerPjButton, viewAllButton, updateClientButton, deleteClientButton);

        // Criando a cena e definindo o tamanho da janela
        Scene scene = new Scene(vbox, 500, 450);
        primaryStage.setTitle("Gerenciamento de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Tela cadastro de Clientes PF
    private void showRegisterPfPjView() {
        RegisterPfPjView registerPfView = SpringContext.getBean(RegisterPfPjView.class);
        registerPfView.start(new Stage());
    }

    //Tela cadastro de Clientes PJ
    private void showRegisterPjView() {
        RegisterPfPjView registerPfView = SpringContext.getBean(RegisterPfPjView.class);
        registerPfView.start(new Stage()); // RegisterPjView
    }

    public static void main(String[] args) {
        launch(args);
    }

}
