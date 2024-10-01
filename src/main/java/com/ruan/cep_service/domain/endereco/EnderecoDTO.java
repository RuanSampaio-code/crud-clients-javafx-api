package com.ruan.cep_service.domain.endereco;

public record EnderecoDTO(String cep,
                          String logradouro,
                          String bairro,
                          String numero,
                          String complemento,
                          String cidade,
                          String uf) {


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
