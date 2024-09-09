package com.ruan.cep_service.domain.endereco;

public record DadosEndereco(

        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String uf,
        String complemento,
        String numero
) {
}
