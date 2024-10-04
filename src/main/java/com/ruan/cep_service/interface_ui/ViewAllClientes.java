package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.ClienteDTO;
import com.ruan.cep_service.service.ClienteService;
import javafx.beans.property.SimpleStringProperty;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
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

    private TableView<ClienteDTO> tabelaClientes;

    public void start(Stage stageTabelaDeClientes) {
        List<ClienteDTO> listaDeClientes = clienteService.findAllClientes();

        // Imprindo no console os clientes
        for (ClienteDTO cliente : listaDeClientes) {
            System.out.println(cliente);
        }

        // Cria uma TableView para exibir os clientes
        tabelaClientes = new TableView<>();

        // Colunas - info do cliente
        TableColumn<ClienteDTO, String> tipoClienteColumn = new TableColumn<>("Tipo de Cliente");
        tipoClienteColumn.setCellValueFactory(cellData ->
                safeValue(cellData.getValue().tipo() != null ? cellData.getValue().tipo().name() : null)
        );

        // Colunas - info do cliente
        TableColumn<ClienteDTO, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nome()));

        TableColumn<ClienteDTO, String> cnpjColumn = new TableColumn<>("CPF/CNPJ");
        cnpjColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cpfcnpj()));

        TableColumn<ClienteDTO, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));

        TableColumn<ClienteDTO, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefone()));


        // Colunas - Endereco do cliente
        TableColumn<ClienteDTO, String> cepColumn = new TableColumn<>("CEP");
        cepColumn.setCellValueFactory(cellData -> {
            String cep = cellData.getValue().endereco() != null ? cellData.getValue().endereco().cep() : null;
            return safeValue(cep);
        });

        TableColumn<ClienteDTO, String> logradouroColumn = new TableColumn<>("Logradouro");
        logradouroColumn.setCellValueFactory(cellData -> {
            String logradouro = cellData.getValue().endereco() != null ? cellData.getValue().endereco().logradouro() : null;
            return safeValue(logradouro);
        });

        TableColumn<ClienteDTO, String> bairroColumn = new TableColumn<>("Bairro");
        bairroColumn.setCellValueFactory(cellData -> {
            String bairro = cellData.getValue().endereco() != null ? cellData.getValue().endereco().bairro() : null;
            return safeValue(bairro);
        });

        TableColumn<ClienteDTO, String> cidadeColumn = new TableColumn<>("Cidade");
        cidadeColumn.setCellValueFactory(cellData -> {
            String cidade = cellData.getValue().endereco() != null ? cellData.getValue().endereco().cidade() : null;
            return safeValue(cidade);
        });

        TableColumn<ClienteDTO, String> ufColumn = new TableColumn<>("UF");
        ufColumn.setCellValueFactory(cellData -> {
            String uf = cellData.getValue().endereco() != null ? cellData.getValue().endereco().uf() : null;
            return safeValue(uf);
        });

        TableColumn<ClienteDTO, String> numeroColumn = new TableColumn<>("Número");
        numeroColumn.setCellValueFactory(cellData -> {
            String numero = cellData.getValue().endereco() != null ? cellData.getValue().endereco().numero() : null;
            return safeValue(numero);
        });

        TableColumn<ClienteDTO, String> complementoColumn = new TableColumn<>("Complemento");
        complementoColumn.setCellValueFactory(cellData -> {
            String complemento = cellData.getValue().endereco() != null ? cellData.getValue().endereco().complemento() : null;
            return safeValue(complemento);
        });


        // Adicionando as colunas na tabela
        tabelaClientes.getColumns().addAll(tipoClienteColumn, nomeColumn, cnpjColumn, emailColumn, telefoneColumn,
                cepColumn, logradouroColumn, bairroColumn, cidadeColumn, ufColumn, numeroColumn, complementoColumn);


        // Outra forma de adicionar
        // Adicionando as colunas na tabela
        // tabelaClientes.getColumns().add(tipoClienteColumn);
        // tabelaClientes.getColumns().add(nomeColumn);
        // tabelaClientes.getColumns().add(cnpjColumn);
        // tabelaClientes.getColumns().add(emailColumn);
        // tabelaClientes.getColumns().add(telefoneColumn);
        // tabelaClientes.getColumns().add(cepColumn);
        // tabelaClientes.getColumns().add(logradouroColumn);
        // tabelaClientes.getColumns().add(bairroColumn);
        // tabelaClientes.getColumns().add(cidadeColumn);
        // tabelaClientes.getColumns().add(ufColumn);
        // tabelaClientes.getColumns().add(numeroColumn);
        // tabelaClientes.getColumns().add(complementoColumn);

        // Adicionando os dados na tabela
        tabelaClientes.getItems().addAll(listaDeClientes);

        // Criar um novo palco para exibir a tabela de clientes
        stageTabelaDeClientes.setTitle("Lista de Clientes");

        // Criar o layout da cena
        StackPane root = new StackPane();
        root.getChildren().add(tabelaClientes);

        // Criar a cena
        Scene scene = new Scene(root, 600, 200);

        // Configurar a cena no novo palco e exibi-lo
        stageTabelaDeClientes.setScene(scene);
        stageTabelaDeClientes.show();
    }

    // Método auxiliar para tratar nulos
    SimpleStringProperty safeValue(String value) {
        return new SimpleStringProperty(value != null && !value.trim().isEmpty() ? value : "Sem conteudo"); // retorna vazio se for null ou vazio
    }
}
