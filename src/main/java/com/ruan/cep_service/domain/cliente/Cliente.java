package com.ruan.cep_service.domain.cliente;

import com.ruan.cep_service.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpfcnpj;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @Embedded
    private Endereco endereco;


}
