package com.ruan.cep_service.domain.cliente;

public enum TipoCliente {

    PESSOA_FISICA("PF", "Pessoa Física"),
    PESSOA_JURIDICA("PJ", "Pessoa Jurídica");

    private final String codigo;
    private final String descricao;

    TipoCliente(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
