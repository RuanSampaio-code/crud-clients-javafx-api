package com.ruan.cep_service.domain.endereco;

public record EnderecoDTO(String cep,
                          String logradouro,
                          String bairro,
                          String cidade ,
                          String uf ,
                          String numero,
                          String complemento) {



}
