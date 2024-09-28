package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteRepository;
import com.ruan.cep_service.domain.endereco.Endereco;
import com.ruan.cep_service.requisicaoViaCep.Requisicao;
import com.ruan.cep_service.service.ClienteService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisterPfView extends Application {

    @Autowired
    private ClienteRepository repository;

    // Será injetado pelo Spring
    @Autowired
    ClienteService clienteService = new ClienteService(repository);


    @Override
    public void start(Stage primaryStage) {

        // Criando os controles da interface
        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();

        Label cnpjLabel = new Label("CNPJ:");
        TextField cnpjField = new TextField();

        // Novos campos: Email e Telefone
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField();

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

        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);

        gridPane.add(telefoneLabel, 0, 3);
        gridPane.add(telefoneField, 1, 3);

        gridPane.add(cepLabel, 0, 4);
        gridPane.add(cepField, 1, 4);
        gridPane.add(searchCepButton, 4, 4);

        gridPane.add(logradouroLabel, 0, 5);
        gridPane.add(logradouroField, 1, 5);

        gridPane.add(bairroLabel, 0, 6);
        gridPane.add(bairroField, 1, 6);

        gridPane.add(cidadeLabel, 0, 7);
        gridPane.add(cidadeField, 1, 7);

        gridPane.add(ufLabel, 0, 8);
        gridPane.add(ufField, 1, 8);

        gridPane.add(numeroLabel, 0, 9);
        gridPane.add(numeroField, 1, 9);

        gridPane.add(complementoLabel, 0, 10);
        gridPane.add(complementoField, 1, 10);
        gridPane.add(saveButton, 1, 11);

        // Definindo a cena e a janela
        Scene scene = new Scene(gridPane, 450, 450);
        primaryStage.setTitle("Cadastro Cliente Pessoa Física");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Adicionando funcionalidades aos botões
        searchCepButton.setOnAction(event -> {
            String cep = cepField.getText();

            //chama classe da api via cep
            Requisicao requisicao = new Requisicao();

            try {
                //Retorna o endereco dos campos da api viacep
                Endereco endereco = requisicao.retornaEndereco(cep);

                logradouroField.setText(endereco.getLogradouro());
                bairroField.setText(endereco.getBairro());
                numeroField.setText(endereco.getNumero());
                complementoField.setText(endereco.getComplemento());
                cidadeField.setText(endereco.getCidade());
                ufField.setText(endereco.getUf());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            String cnpj = cnpjField.getText();
            String cep = cepField.getText();
            String logradouro = logradouroField.getText();
            String bairro = bairroField.getText();
            String cidade = cidadeField.getText();
            String uf = ufField.getText();
            String numero = numeroField.getText();
            String complemento = complementoField.getText();

            Endereco endereco = new Endereco();
            endereco.setCep(cep);
            endereco.setLogradouro(logradouro);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setUf(uf);
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);

            Cliente cliente = new Cliente();
            cliente.setNome(name);
            cliente.setCpfcnpj(cnpj);
            cliente.setTelefone(numero);
            cliente.setEndereco(endereco);

            // Chamando
            //ClienteService clienteService = new ClienteService();
            clienteService.salvarCliente(cliente);

            // Mostrando a mensagem de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText(null);
            alert.setContentText("Cliente cadastrado com sucesso!");
            alert.showAndWait();

            // Lógica para mostrar os dados do cliente
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
