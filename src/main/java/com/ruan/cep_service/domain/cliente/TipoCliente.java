package com.ruan.cep_service.domain.cliente;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCliente {

    PESSOA_FISICA("PF", "Pessoa Física"),
    PESSOA_JURIDICA("PJ", "Pessoa Jurídica");

    private final String codigo;
    private final String descricao;

    TipoCliente(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }



    @JsonValue
    public String getCodigo() {
        return codigo;
    }


    @JsonCreator
    public static TipoCliente fromCodigo(String codigo) {

        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("O código do tipo de cliente não pode ser nulo ou vazio.");
        }
        for (TipoCliente tipo : TipoCliente.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de cliente desconhecido: " + codigo);
    }
}
