package com.ruan.cep_service.requisicaoViaCep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruan.cep_service.domain.endereco.Endereco;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class Requisicao {

    public Endereco retornaEndereco(String cep) throws IOException, InterruptedException {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Usa Jackson para converter a string JSON em um objeto Endereco
        ObjectMapper objectMapper = new ObjectMapper();
        Endereco endereco = objectMapper.readValue(response.body(), Endereco.class);

        //Retorna formato de endereco
        return endereco;

    }
}
