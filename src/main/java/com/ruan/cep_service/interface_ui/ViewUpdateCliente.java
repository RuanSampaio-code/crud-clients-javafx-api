package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteDTO;
import com.ruan.cep_service.domain.endereco.Endereco;
import com.ruan.cep_service.service.ClienteService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewUpdateCliente {

    @Autowired
    private ClienteService clienteService;

    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Campo para buscar cliente por CPF/CNPJ
        Label buscarCpfCnpjLabel = new Label("CPF/CNPJ do Cliente:");
        TextField buscarCpfCnpjField = new TextField();
        Button buscarButton = new Button("Buscar Cliente");

        // Campos para entrada de dados do cliente
        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField();

        Label cpfcnpjLabel = new Label("CPF/CNPJ:");
        TextField cpfcnpjField = new TextField();
        cpfcnpjField.setEditable(false);

        Label tipoLabel = new Label("Tipo:");
        TextField tipoField = new TextField();

        // Campos para entrada de dados do endereço
        Label cepLabel = new Label("CEP:");
        TextField cepField = new TextField();

        Label logradouroLabel = new Label("Logradouro:");
        TextField logradouroField = new TextField();

        Label bairroLabel = new Label("Bairro:");
        TextField bairroField = new TextField();

        Label cidadeLabel = new Label("Cidade:");
        TextField cidadeField = new TextField();

        Label ufLabel = new Label("UF:");
        TextField ufField = new TextField();

        Label numeroLabel = new Label("Número:");
        TextField numeroField = new TextField();

        Label complementoLabel = new Label("Complemento:");
        TextField complementoField = new TextField();

        Button updateButton = new Button("Atualizar Cliente");
        updateButton.setDisable(true); // Inicialmente desabilitado

        // Ação para buscar o cliente
        buscarButton.setOnAction(event -> {
            String cpfCnpj = buscarCpfCnpjField.getText();
            Optional<ClienteDTO> clienteOptional = clienteService.buscarClientePorId(cpfCnpj);

            if (clienteOptional.isPresent()) {
                ClienteDTO cliente = clienteOptional.get();
                nomeField.setText(cliente.nome());
                emailField.setText(cliente.email());
                telefoneField.setText(cliente.telefone());
                cpfcnpjField.setText(cliente.cpfcnpj());
                tipoField.setText(String.valueOf(cliente.tipo()));

                // Preencher os campos de endereço
                if (cliente.endereco() != null) {
                    cepField.setText(cliente.endereco().cep());
                    logradouroField.setText(cliente.endereco().logradouro());
                    bairroField.setText(cliente.endereco().bairro());
                    cidadeField.setText(cliente.endereco().cidade());
                    ufField.setText(cliente.endereco().uf());
                    numeroField.setText(cliente.endereco().numero());
                    complementoField.setText(cliente.endereco().complemento());
                }

                updateButton.setDisable(false); // Habilita o botão de atualização
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cliente não encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Nenhum cliente foi encontrado com o CPF/CNPJ informado.");
                alert.showAndWait();
            }
        });

        // Ação de atualização ao clicar no botão
        updateButton.setOnAction(event -> {
            Cliente clienteAtualizado = new Cliente();
            clienteAtualizado.setCpfcnpj(buscarCpfCnpjField.getText());
            clienteAtualizado.setNome(nomeField.getText());
            clienteAtualizado.setEmail(emailField.getText());
            clienteAtualizado.setTelefone(telefoneField.getText());

            // Criar um novo endereço com os dados atualizados
            Endereco enderecoAtualizado = new Endereco();
            enderecoAtualizado.setCep(cepField.getText());
            enderecoAtualizado.setLogradouro(logradouroField.getText());
            enderecoAtualizado.setBairro(bairroField.getText());
            enderecoAtualizado.setCidade(cidadeField.getText());
            enderecoAtualizado.setUf(ufField.getText());
            enderecoAtualizado.setNumero(numeroField.getText());
            enderecoAtualizado.setComplemento(complementoField.getText());

            clienteAtualizado.setEndereco(enderecoAtualizado);

            clienteService.atualizarCliente(cpfcnpjField.getText(), clienteAtualizado);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualização Concluída");
            alert.setHeaderText(null);
            alert.setContentText("Cliente atualizado com sucesso!");
            alert.showAndWait();
        });

        // Adiciona os componentes ao GridPane
        gridPane.add(buscarCpfCnpjLabel, 0, 0);
        gridPane.add(buscarCpfCnpjField, 1, 0);
        gridPane.add(buscarButton, 2, 0);

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

        // Campos de endereço
        gridPane.add(cepLabel, 0, 6);
        gridPane.add(cepField, 1, 6);
        gridPane.add(logradouroLabel, 0, 7);
        gridPane.add(logradouroField, 1, 7);
        gridPane.add(bairroLabel, 0, 8);
        gridPane.add(bairroField, 1, 8);
        gridPane.add(cidadeLabel, 0, 9);
        gridPane.add(cidadeField, 1, 9);
        gridPane.add(ufLabel, 0, 10);
        gridPane.add(ufField, 1, 10);
        gridPane.add(numeroLabel, 0, 11);
        gridPane.add(numeroField, 1, 11);
        gridPane.add(complementoLabel, 0, 12);
        gridPane.add(complementoField, 1, 12);

        gridPane.add(updateButton, 1, 13);

        Scene scene = new Scene(gridPane, 500, 600);
        stage.setTitle("Atualizar Cliente");
        stage.setScene(scene);
        stage.show();
    }
}
