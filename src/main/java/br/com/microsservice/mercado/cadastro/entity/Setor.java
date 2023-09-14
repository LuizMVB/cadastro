package br.com.microsservice.mercado.cadastro.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

@Entity
@Table
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private Boolean isAtivo;

}
