package com.ruan.cep_service.requisicaoViaCep;

import com.ruan.cep_service.domain.endereco.Endereco;
import com.ruan.cep_service.domain.endereco.EnderecoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class Requisicao {

    private final RestTemplate restTemplate;

    public Requisicao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Endereco retornaEndereco(String cep) throws IOException, InterruptedException {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        // Recebe o DTO da API
        EnderecoDTO enderecoDTO = restTemplate.getForObject(url, EnderecoDTO.class);

        Endereco endereco = converteParaEndereco(enderecoDTO);

        //Retorna formato de endereco
        return endereco;

    }

    private Endereco converteParaEndereco(EnderecoDTO enderecoDTO) {

        Endereco endereco = new Endereco();
        endereco.setCep(enderecoDTO.cep());
        endereco.setLogradouro(enderecoDTO.logradouro());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setUf(enderecoDTO.uf());

        return endereco;
    }
}
