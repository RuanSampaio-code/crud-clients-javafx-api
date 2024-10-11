package com.ruan.cep_service.interface_ui;

import com.ruan.cep_service.domain.cliente.ClienteDTO;
import com.ruan.cep_service.service.ClienteService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewDeleteCliente {

    @Autowired
    private ClienteService clienteService;

    public void start(Stage stage) {
        // Configurando o layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Campos e botões
        Label idLabel = new Label("CPF/CNPJ do Cliente:");
        TextField cpfcpjField = new TextField();
        Button viewButton = new Button("Buscar Cliente");

        // Ação do botão "Buscar Cliente"
        viewButton.setOnAction(event -> {
            String cpfcnpjStr = cpfcpjField.getText().trim();

            if (cpfcnpjStr.isEmpty()) {
                // Alerta para campo vazio
                showAlert("Erro", "Por favor, insira um CPF/CNPJ válido.", Alert.AlertType.ERROR);
                return;
            }

            // Busca o cliente pelo CPF/CNPJ
            Optional<ClienteDTO> optionalCliente = clienteService.buscarClientePorId(cpfcnpjStr);

            if (optionalCliente.isPresent()) {
                ClienteDTO clienteDTO = optionalCliente.get();

                // Confirmação de exclusão
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmação de Exclusão");
                confirmationAlert.setHeaderText("Deseja realmente excluir este cliente?");
                confirmationAlert.setContentText("Nome: " + clienteDTO.nome() + "\nEmail: " + clienteDTO.email());

                // Verificar a resposta do usuário
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Exclusão lógica do cliente
                    clienteService.deletarClientePorCpfCnpj(cpfcnpjStr);
                    showAlert("Sucesso", "Cliente excluído com sucesso!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Cancelado", "A exclusão foi cancelada.", Alert.AlertType.INFORMATION);
                }
            } else {
                // Mensagem de erro caso o cliente não seja encontrado
                showAlert("Erro", "Cliente não encontrado para o CPF/CNPJ informado.", Alert.AlertType.ERROR);
            }
        });

        // Adicionando os elementos ao layout
        gridPane.add(idLabel, 0, 0);
        gridPane.add(cpfcpjField, 1, 0);
        gridPane.add(viewButton, 1, 1);

        // Configurando a cena e exibindo o estágio
        Scene scene = new Scene(gridPane, 400, 200);
        stage.setTitle("Excluir Cliente");
        stage.setScene(scene);
        stage.show();
    }

    // Método para exibir alertas
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
