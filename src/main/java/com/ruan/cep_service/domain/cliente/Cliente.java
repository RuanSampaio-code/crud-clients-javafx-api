package com.ruan.cep_service.domain.cliente;

import com.ruan.cep_service.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@ToString
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
