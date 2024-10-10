package com.ruan.cep_service.configSpringJavaFx;

import com.ruan.cep_service.interface_ui.RegisterPfPjView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJavaFxApplication extends Application {
    private static ApplicationContext context;

    public static void main(String[] args) {
        // Inicia o contexto do Spring
        context = SpringApplication.run(SpringJavaFxApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Aqui você pode obter o seu controlador ou view
        RegisterPfPjView registerPfView = context.getBean(RegisterPfPjView.class);
        registerPfView.start(primaryStage);
    }
}