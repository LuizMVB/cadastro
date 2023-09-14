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

    @Column
    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;

    @OneToMany
    @JoinColumn(name = "id_setores", referencedColumnName = "id")
    private List<Setor> setorList;

    @OneToMany
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private List<Produto> produtoList;

}
