package br.com.microsservice.mercado.cadastro.entity;

import br.com.microsservice.mercado.cadastro.domain.TipoEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.List;

@Entity
@Table
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(length = 40)
    private String nome;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;

    @ManyToOne
    @JoinColumn(name = "idMercado")
    private Mercado mercado;

    @OneToMany(mappedBy = "ofertante")
    private List<OfertaSetor> ofertaSetorList;

    @OneToMany(mappedBy = "ofertante")
    private List<OfertaProduto> ofertaProdutoList;

}
