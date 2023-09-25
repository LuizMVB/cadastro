package br.com.microsservice.mercado.domain.entity;

import br.com.microsservice.mercado.domain.Entidade;
import br.com.microsservice.mercado.domain.TipoEnum;
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
    private TipoEnum tipo;

    @Column(nullable = false)
    private Boolean isAtivo = true;

    @Embedded
    private DataEmbeddable data = new DataEmbeddable();

    @ManyToOne
    @JoinColumn(name = "id_mercado", updatable = false)
    private Mercado mercado;

    public void desativar() {
        isAtivo = false;
    }
}
