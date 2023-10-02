package br.com.microsservice.mercadoms.domain.entity;

import br.com.microsservice.mercadoms.domain.TipoFilialEnum;
import br.com.microsservice.mercadoms.domain.event.OnChangeIsAtivoFilialEvent;
import br.com.microsservice.mercadoms.domain.event.VerificacaoMercadoIsAtivoFilialEvent;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Filial extends AbstractAggregateRoot<Filial> {

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

    @Transient
    private Boolean lastIsAtivo;

    public Filial() {
        registerEvent(new VerificacaoMercadoIsAtivoFilialEvent(id));
    }

    public void setIsAtivo(Boolean ativo) {
        lastIsAtivo = isAtivo;
        isAtivo = ativo;
        registerEvent(new OnChangeIsAtivoFilialEvent(id, isAtivo, lastIsAtivo));
    }

    public void desativar() {
        isAtivo = false;
    }

}
