package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteDTO;
import com.ruan.cep_service.service.ClienteService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ViewOneCliente {

    @Autowired
    private ClienteService clienteService;

    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label idLabel = new Label("CPF/CNPJ do Cliente:");
        TextField idField = new TextField();
        Button viewButton = new Button("Ver Cliente");

        // Labels para exibir informações do cliente
        Label nomeLabel = new Label("Nome:");
        Label nomeValue = new Label();

        Label emailLabel = new Label("Email:");
        Label emailValue = new Label();

        Label telefoneLabel = new Label("Telefone:");
        Label telefoneValue = new Label();

        Label cpfcnpjLabel = new Label("CPF/CNPJ:");
        Label cpfcnpjValue = new Label();


        // Labels para exibir informações do endereço
        Label cepLabel = new Label("CEP:");
        Label cepValue = new Label();

        Label logradouroLabel = new Label("Logradouro:");
        Label logradouroValue = new Label();

        Label bairroLabel = new Label("Bairro:");
        Label bairroValue = new Label();

        Label cidadeLabel = new Label("Cidade:");
        Label cidadeValue = new Label();

        Label ufLabel = new Label("UF:");
        Label ufValue = new Label();

        Label numeroLabel = new Label("Número:");
        Label numeroValue = new Label();

        Label complementoLabel = new Label("Complemento:");
        Label complementoValue = new Label();

        // Ação ao clicar no botão para visualizar o cliente
        viewButton.setOnAction(event -> {
            String idStr = idField.getText();

            try {
                String cpfcnpj = idStr.trim(); // Usa o CPF/CNPJ diretamente
                Optional<ClienteDTO> optionalCliente = clienteService.buscarClientePorId(cpfcnpj);

                if (optionalCliente.isPresent()) {
                    ClienteDTO cliente = optionalCliente.get();

                    // Atualiza os labels com as informações do cliente
                    nomeValue.setText(cliente.nome());
                    emailValue.setText(cliente.email()  != null ? cliente.email() : "N/A");
                    telefoneValue.setText(cliente.telefone());
                    cpfcnpjValue.setText(cliente.cpfcnpj());

                    // Atualiza os labels com as informações do endereço
                    cepValue.setText(!Objects.equals(cliente.endereco().cep(), "") ? cliente.endereco().cep() : "N/A");
                    logradouroValue.setText(cliente.endereco() != null ? cliente.endereco().logradouro() : "N/A");
                    bairroValue.setText(cliente.endereco() != null ? cliente.endereco().bairro() : "N/A");
                    cidadeValue.setText(cliente.endereco() != null ? cliente.endereco().cidade() : "N/A");
                    ufValue.setText(cliente.endereco() != null ? cliente.endereco().uf() : "N/A");
                    numeroValue.setText(cliente.endereco() != null ? cliente.endereco().numero() : "N/A");
                    complementoValue.setText(cliente.endereco() != null ? cliente.endereco().complemento() : "N/A");



                } else {
                    showAlert("Cliente não encontrado", "Nenhum cliente foi encontrado com o CPF/CNPJ: " + cpfcnpj);
                }
            } catch (Exception e) {
                showAlert("Erro", "Ocorreu um erro ao buscar o cliente: " + e.getMessage());
            }
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(viewButton, 2, 0);
        gridPane.add(nomeLabel, 0, 1);
        gridPane.add(nomeValue, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailValue, 1, 2);
        gridPane.add(telefoneLabel, 0, 3);
        gridPane.add(telefoneValue, 1, 3);
        gridPane.add(cpfcnpjLabel, 0, 4);
        gridPane.add(cpfcnpjValue, 1, 4);


        // Adicionando os labels do endereço
        gridPane.add(cepLabel, 0, 5);
        gridPane.add(cepValue, 1, 5);
        gridPane.add(logradouroLabel, 0, 6);
        gridPane.add(logradouroValue, 1, 6);
        gridPane.add(bairroLabel, 0, 7);
        gridPane.add(bairroValue, 1, 7);
        gridPane.add(cidadeLabel, 0, 8);
        gridPane.add(cidadeValue, 1, 8);
        gridPane.add(ufLabel, 0, 9);
        gridPane.add(ufValue, 1, 9);
        gridPane.add(numeroLabel, 0, 10);
        gridPane.add(numeroValue, 1, 10);
        gridPane.add(complementoLabel, 0, 11);
        gridPane.add(complementoValue, 1, 11);


        Scene scene = new Scene(gridPane, 400, 400);
        stage.setTitle("Visualizar Cliente");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
