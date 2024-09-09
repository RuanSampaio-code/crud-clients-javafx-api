# crud-clients-javafx-api
# CRUD de Clientes com JavaFX

Este projeto é uma aplicação de interface gráfica desenvolvida em Java utilizando JavaFX, que permite o gerenciamento de clientes. A aplicação permite cadastrar, editar, excluir e visualizar clientes, diferenciando entre Pessoa Física (CPF) e Pessoa Jurídica (CNPJ). Além disso, a aplicação possui integração com a API Via CEP para buscar informações de endereço ao inserir o CEP.

## Funcionalidades

- **Cadastro de Clientes**: Suporte para clientes do tipo Pessoa Física (CPF) e Pessoa Jurídica (CNPJ).
- **Consulta de CEP**: Integração com a API Via CEP para buscar automaticamente informações de endereço.
- **Operações CRUD**: Criação, Leitura, Atualização e Exclusão de registros de clientes no banco de dados.
- **Banco de Dados**: Utilização de um banco de dados PostgreSQL para armazenar os dados de clientes.
- **Interface Gráfica**: Desenvolvida utilizando JavaFX para uma experiência de usuário amigável.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **JavaFX**: Biblioteca gráfica para a interface de usuário.
- **Spring Boot**: Framework utilizado para a criação da API backend.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos clientes.
- **API Via CEP**: Utilizada para busca de informações de endereço com base no CEP.
- **Maven**: Gerenciador de dependências e build.
- **Lombok**: Para reduzir o código boilerplate, como getters e setters.
