package com.ruan.cep_service.domain.cliente;

import com.ruan.cep_service.domain.endereco.EnderecoDTO;


public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpfcnpj,
        TipoCliente tipo,
        EnderecoDTO endereco
) {

     //Construtor que recebe a entidade Cliente
//    public ClienteDTO(Cliente cliente) {
//        this(
//                cliente.getId(),
//                cliente.getNome(),
//                cliente.getEmail(),
//                cliente.getTelefone(),
//                cliente.getCpfcnpj(),
//                new EnderecoDTO(cliente.getEndereco())  // Conversão do Endereco para DTO
//        );
//    }
}