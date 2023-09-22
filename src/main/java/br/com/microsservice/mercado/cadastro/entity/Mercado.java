package br.com.microsservice.mercado.cadastro.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

@Entity
@Table
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(length = 14)
    private String cnpj;

    @Column(length = 40)
    private String nome;

    @Embedded
    private DataEmbeddable data;

}
