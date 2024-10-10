package com.ruan.cep_service.domain.endereco;

public record EnderecoDTO(String cep,
                          String logradouro,
                          String bairro,
                          String cidade ,
                          String uf ,
                          String numero,
                          String complemento) {


    // Construtor que aceita a entidade Endereco
    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getUf()
        );
    }

}
