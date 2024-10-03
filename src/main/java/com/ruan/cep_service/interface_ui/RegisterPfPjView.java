package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteRepository;
import com.ruan.cep_service.domain.cliente.TipoCliente;
import com.ruan.cep_service.domain.endereco.Endereco;
import com.ruan.cep_service.requisicaoViaCep.Requisicao;
import com.ruan.cep_service.service.ClienteService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.PrivilegedAction;

@Component
public class RegisterPfPjView {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RestTemplate restTemplate;

    private ComboBox<String> tipoClienteComboBox; // Novo campo para selecionar o tipo de cliente
    private TextField nameField;
    private TextField cnpjField;
    private TextField emailField;
    private TextField telefoneField;
    private TextField cepField;
    private TextField logradouroField;
    private TextField bairroField;
    private TextField cidadeField;
    private TextField ufField;
    private TextField numeroField;
    private TextField complementoField;


    public void start(Stage primaryStage) {

        // Criando os controles da interface
        Label tipoClienteLabel = new Label("Tipo de Cliente:");
        tipoClienteComboBox = new ComboBox<>();
        tipoClienteComboBox.getItems().addAll("PF", "PJ"); // Adiciona as opções
        tipoClienteComboBox.setValue(""); // Define um valor padrão

        ComboBox<TipoCliente> tipoClienteComboBox = new ComboBox<>();
        tipoClienteComboBox.getItems().addAll(TipoCliente.PESSOA_FISICA, TipoCliente.PESSOA_JURIDICA);


        // Criando os controles da interface
        Label nameLabel = new Label("Nome:");
        nameField = new TextField();

        Label cnpjLabel = new Label("CPF/CNPJ:");
        cnpjField = new TextField();

        // Novos campos: Email e Telefone
        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label telefoneLabel = new Label("Telefone:");
        telefoneField = new TextField();

        Label cepLabel = new Label("CEP:");
        cepField = new TextField();
        Button searchCepButton = new Button("Buscar Endereço");

        // Campos de endereço
        Label logradouroLabel = new Label("Logradouro:");
        logradouroField = new TextField();

        Label bairroLabel = new Label("Bairro:");
        bairroField = new TextField();

        Label cidadeLabel = new Label("Cidade:");
        cidadeField = new TextField();

        Label ufLabel = new Label("UF:");
        ufField = new TextField();

        // Campos Número e Complemento - movidos para baixo
        Label numeroLabel = new Label("Número:");
        numeroField = new TextField();

        Label complementoLabel = new Label("Complemento:");
        complementoField = new TextField();

        Button saveButton = new Button("Salvar");

        // Criando o GridPane e adicionando os controles
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Adicionando os componentes ao GridPane
        gridPane.add(tipoClienteLabel, 0, 0);
        gridPane.add(tipoClienteComboBox, 1, 0); // Adiciona o ComboBox

        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);

        gridPane.add(cnpjLabel, 0, 2);
        gridPane.add(cnpjField, 1, 2);

        gridPane.add(emailLabel, 0, 3);
        gridPane.add(emailField, 1, 3);

        gridPane.add(telefoneLabel, 0, 4);
        gridPane.add(telefoneField, 1, 4);

        // Campos de endereco
        gridPane.add(cepLabel, 0, 5);
        gridPane.add(cepField, 1, 5);
        gridPane.add(searchCepButton, 2, 5);
        gridPane.add(logradouroLabel, 0, 6);
        gridPane.add(logradouroField, 1, 6);
        gridPane.add(bairroLabel, 0, 7);
        gridPane.add(bairroField, 1, 7);
        gridPane.add(cidadeLabel, 0, 8);
        gridPane.add(cidadeField, 1, 8);
        gridPane.add(ufLabel, 0, 9);
        gridPane.add(ufField, 1, 9);
        gridPane.add(numeroLabel, 0, 10);
        gridPane.add(numeroField, 1, 10);
        gridPane.add(complementoLabel, 0, 11);
        gridPane.add(complementoField, 1, 11);
        gridPane.add(saveButton, 1, 12);

        // Definindo a cena e a janela
        Scene scene = new Scene(gridPane, 450, 450);
        primaryStage.setTitle("Cadastro Cliente Pessoa Física");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Adicionando funcionalidades aos botões
        searchCepButton.setOnAction(event -> {
            String cep = cepField.getText();
            Requisicao requisicao = new Requisicao(restTemplate);

            try {
                Endereco endereco = requisicao.retornaEndereco(cep);
                logradouroField.setText(endereco.getLogradouro());
                bairroField.setText(endereco.getBairro());
                numeroField.setText(endereco.getNumero());
                complementoField.setText(endereco.getComplemento());
                cidadeField.setText(endereco.getCidade());
                ufField.setText(endereco.getUf());
            } catch (IOException | InterruptedException e) {
                showAlert("Erro", "Não foi possível buscar o endereço. Verifique o CEP e tente novamente.");
            }
        });

        saveButton.setOnAction(event -> {
            String tipoCliente = String.valueOf(tipoClienteComboBox.getValue()); // Obtém o tipo de cliente
            String name = nameField.getText();
            String cnpj = cnpjField.getText();
            String email = emailField.getText();
            String telefone = telefoneField.getText();
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
            cliente.setTipo(TipoCliente.valueOf(tipoCliente)); // Adiciona o tipo de cliente
            cliente.setNome(name);
            cliente.setCpfcnpj(cnpj);
            cliente.setEmail(email);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);

            clienteService.salvarCliente(cliente);
            showAlert("Sucesso", "Cliente cadastrado com sucesso!");

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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
