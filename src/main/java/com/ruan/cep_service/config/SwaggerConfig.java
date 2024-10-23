package com.ruan.cep_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("crud-javafx-api-viacep")
                .version("1.0.0")
                .description("Esta API permite o gerenciamento de clientes e a consulta de endereços a partir de um CEP. " +
                        "Oferece funcionalidades para cadastrar, consultar, atualizar e excluir clientes, além de buscar " +
                        "informações de endereço automaticamente, com base no CEP informado.\n\n" +
                        "### Funcionalidades principais:\n" +
                        "- **Cadastro de Clientes**: Insere novos clientes no sistema com informações como nome, tipo, CPF/CNPJ, etc.\n" +
                        "- **Consulta de Clientes**: Recupera informações de clientes armazenados.\n" +
                        "- **Atualização e Exclusão**: Modifica ou remove registros de clientes.\n" +
                        "- **Consulta de Endereço por CEP**: Realiza buscas automáticas de endereço a partir do CEP do cliente usando serviços externos.\n\n" +
                        "### Exemplos de Endpoints:\n" +
                        "- `POST /clientes`: Cria um novo cliente.\n" +
                        "- `GET /clientes/{id}`: Retorna os detalhes de um cliente específico.\n" +
                        "- `PUT /clientes/{id}`: Atualiza os dados de um cliente.\n" +
                        "- `DELETE /clientes/{id}`: Exclui um cliente do sistema.\n" +
                        "- `GET /clientes/cep/{cep}`: Busca e preenche automaticamente os dados de endereço com base no CEP informado.\n\n" +
                        "### Tecnologias Utilizadas:\n" +
                        "- **Java** e **Spring Boot**\n" +
                        "- **API ViaCEP** para busca de endereço por CEP\n" +
                        "- **Swagger** para documentação e teste dos endpoints"
                ));
    }
}
