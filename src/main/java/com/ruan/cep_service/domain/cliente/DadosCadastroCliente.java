package com.ruan.cep_service.domain.cliente;

import com.ruan.cep_service.domain.endereco.DadosEndereco;


public record DadosCadastroCliente(
        Long id,
        String nome,
        String cidade,
        String email,
        String telefone,
        String cpfcnpj,

        TipoCliente tipoCliente,
        DadosEndereco dadosEndereco) {
}
