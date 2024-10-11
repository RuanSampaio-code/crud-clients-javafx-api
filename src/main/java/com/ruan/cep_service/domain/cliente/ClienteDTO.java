package com.ruan.cep_service.domain.cliente;

import com.ruan.cep_service.domain.endereco.EnderecoDTO;


public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpfcnpj,
        TipoCliente tipo,
        //Boolean ativo,
        EnderecoDTO endereco
) {


}