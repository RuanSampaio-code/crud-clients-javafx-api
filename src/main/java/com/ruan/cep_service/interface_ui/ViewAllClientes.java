package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.service.ClienteService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

        // Imprindo no console os clientes
        for (Cliente cliente : listaDeClientes) {
            System.out.println(cliente);
        }

        // Cria uma TableView para exibir os clientes
        tabelaClientes = new TableView<>();

        // Colunas - info do cliente
        TableColumn<Cliente, String> tipoClienteColumn = new TableColumn<>("Tipo de Cliente");
        tipoClienteColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Cliente, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cliente, String> cnpjColumn = new TableColumn<>("CPF/CNPJ");
        cnpjColumn.setCellValueFactory(new PropertyValueFactory<>("cpfcnpj"));

        TableColumn<Cliente, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Cliente, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));


        // Colunas - Endereco do cliente
        TableColumn<Cliente, String> cepColumn = new TableColumn<>("CEP");
        cepColumn.setCellValueFactory(cellData -> {
            String cep = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getCep() : null;
            return safeValue(cep);
        });

        TableColumn<Cliente, String> logradouroColumn = new TableColumn<>("Logradouro");
        logradouroColumn.setCellValueFactory(cellData -> {
            String logradouro = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getLogradouro() : null;
            return safeValue(logradouro);
        });

        TableColumn<Cliente, String> bairroColumn = new TableColumn<>("Bairro");
        bairroColumn.setCellValueFactory(cellData -> {
            String bairro = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getBairro() : null;
            return safeValue(bairro);
        });

        TableColumn<Cliente, String> cidadeColumn = new TableColumn<>("Cidade");
        cidadeColumn.setCellValueFactory(cellData -> {
            String cidade = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getCidade() : null;
            return safeValue(cidade);
        });

        TableColumn<Cliente, String> ufColumn = new TableColumn<>("UF");
        ufColumn.setCellValueFactory(cellData -> {
            String uf = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getUf() : null;
            return safeValue(uf);
        });

        TableColumn<Cliente, String> numeroColumn = new TableColumn<>("Número");
        numeroColumn.setCellValueFactory(cellData -> {
            String numero = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getNumero() : null;
            return safeValue(numero);
        });

        TableColumn<Cliente, String> complementoColumn = new TableColumn<>("Complemento");
        complementoColumn.setCellValueFactory(cellData -> {
            String complemento = cellData.getValue().getEndereco() != null ? cellData.getValue().getEndereco().getComplemento() : null;
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
