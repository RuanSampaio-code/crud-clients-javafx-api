package com.ruan.cep_service.domain.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;

    private String numero;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("localidade")
    private String cidade;
    private String uf;

//    public Endereco(EnderecoDTO dados) {
//        this.logradouro = dados.logradouro();
//        this.bairro = dados.bairro();
//        this.cep = dados.cep();
//        this.uf = dados.uf();
//        this.cidade = dados.cidade();
//        this.numero = dados.numero();
//        this.complemento = dados.complemento();
//    }


}
