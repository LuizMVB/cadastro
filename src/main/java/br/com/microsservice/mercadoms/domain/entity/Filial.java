package br.com.microsservice.mercadoms.domain.entity;

import br.com.microsservice.mercadoms.domain.Entidade;
import br.com.microsservice.mercadoms.domain.TipoFilialEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Filial extends Entidade<Filial> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoFilialEnum tipo;

    @Column(nullable = false)
    private Boolean isAtivo = true;

    @Embedded
    private Endereco endereco;

    @Embedded
    private DataEmbeddable data = new DataEmbeddable();

    @ManyToOne
    @JoinColumn(name = "id_mercado", updatable = false)
    private Mercado mercado;

    public void desativar() {
        isAtivo = false;
    }
}
