package com.ruan.cep_service;

import com.ruan.cep_service.configSpringJavaFx.SpringContext;
import com.ruan.cep_service.interface_ui.RegisterPfPjView;
import com.ruan.cep_service.interface_ui.ViewAllClientes;
import com.ruan.cep_service.interface_ui.ViewOneCliente;
import com.ruan.cep_service.interface_ui.ViewUpdateCliente;
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
        Button registerPfButton = new Button("Cadastro de Clientes");
        Button viewAllButton = new Button("Ver Todos os Clientes");
        Button viewOneClienteButton = new Button("Procurar Cliente");
        Button updateClientButton = new Button("Alterar Cliente");
        Button deleteClientButton = new Button("Deletar Cliente");

        // Adicionando ação aos botões
        registerPfButton.setOnAction(event -> showRegisterPfPjView());
        viewAllButton.setOnAction(event -> showViewAllClientsView());
        viewOneClienteButton.setOnAction(event -> showViewOneCliente());
        updateClientButton.setOnAction(event -> showUpdateClientView());
//        deleteClientButton.setOnAction(event -> showDeleteClientView());

        // Criando o layout (VBox) e adicionando os botões
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(registerPfButton, viewAllButton,viewOneClienteButton, updateClientButton, deleteClientButton);

        // Criando a cena e definindo o tamanho da janela
        Scene scene = new Scene(vbox, 500, 450);
        primaryStage.setTitle("Gerenciamento de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Tela cadastro de Clientes
    private void showRegisterPfPjView() {
        RegisterPfPjView registerPfView = SpringContext.getBean(RegisterPfPjView.class);
        registerPfView.start(new Stage());
    }

    //Tela de visualização de todos os clientes
    private void showViewAllClientsView(){

        ViewAllClientes viewAllClientes = SpringContext.getBean(ViewAllClientes.class);
        viewAllClientes.start(new Stage());

    }

    //Prucurar um cliente
    private void showViewOneCliente(){
        ViewOneCliente viewOneCliente = SpringContext.getBean(ViewOneCliente.class);
        viewOneCliente.start(new Stage());

    }

    //Tela de alteraçao de clientes especifico
    private void showUpdateClientView(){
        ViewUpdateCliente viewUpdateCliente = SpringContext.getBean(ViewUpdateCliente.class);
        viewUpdateCliente.start(new Stage());

    }

    public static void main(String[] args) {
        launch(args);
    }

}
