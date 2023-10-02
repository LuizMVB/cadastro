package br.com.microsservice.mercadoms.domain.entity;

import br.com.microsservice.mercadoms.domain.Entidade;
import br.com.microsservice.mercadoms.domain.TipoFilialEnum;
import br.com.microsservice.mercadoms.domain.event.DesativacaoMercadoFilialEvent;
import br.com.microsservice.mercadoms.domain.event.ValidacaoMercadoIsAtivoPorIdFilialEvent;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Filial extends AbstractAggregateRoot<Filial> implements Entidade {

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

    public void validarSePodePersistirAtualizacao() {
        if(id == null) {
            throw new IllegalStateException("O id da filial está nulo: para realizar " +
                    "a atualização do mercado, o id da filial não pode estar nulo");
        }

        registerEvent(new ValidacaoMercadoIsAtivoPorIdFilialEvent(id));
    }

    public void atualizarMercado() {
        if(isAtivo) return;

        if(id == null) {
            throw new IllegalStateException("O id da filial está nulo: para realizar " +
                    "a atualização do mercado, o id da filial não pode estar nulo");
        }

        registerEvent(new DesativacaoMercadoFilialEvent(id));
    }

    public void desativar() {
        isAtivo = false;
    }

}
