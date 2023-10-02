package br.com.microsservice.mercadoms.domain.entity;

import br.com.microsservice.mercadoms.domain.Entidade;
import br.com.microsservice.mercadoms.domain.event.AtualizacaoFilialListPorIdMercadoEIsAtivoEvent;
import br.com.microsservice.mercadoms.domain.event.ValidacaoMercadoIsAtivoPorIdEIsAtivoEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.*;

@Entity
@Table
@Data
public class Mercado extends AbstractAggregateRoot<Mercado> implements Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 14, nullable = false)
    private String cnpj;

    @Column(length = 40, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean isAtivo = true;

    @Embedded
    private DataEmbeddable data = new DataEmbeddable();

    @OneToMany(mappedBy = "mercado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Filial> filialList;

    public void setFilialList(@NotNull List<Filial> filialList) {
        this.filialList = filialList;
        this.filialList.forEach(filial -> filial.setMercado(this));
    }

    public void addFilial(@NotNull Filial filial) {
        filial.setMercado(this);
        filialList.add(filial);
    }

    public void atualizarFilialList() {
        if(id == null) {
            throw new IllegalStateException("O id do mercado está nulo: para realizar a " +
                    "atualização do campo filialList o id não pode ser nulo");
        }

        if(isAtivo == null) {
            throw new IllegalStateException("O campo isAtivo do mercado está nulo: para realizar " +
                    "a validação do mercado, o campo isAtivo não pode estar nulo");
        }

        registerEvent(new AtualizacaoFilialListPorIdMercadoEIsAtivoEvent(this));
    }

    public List<Filial> getFiliaisAtivas() {
        return filialList.stream()
                .filter(Filial::getIsAtivo)
                .toList();
    }

    public void validarSePodePersistirAtualizacao() {
        if(id == null) {
            throw new IllegalStateException("O id do mercado está nulo: para realizar " +
                    "a validação do mercado, o id não pode estar nulo");
        }

        if(isAtivo == null) {
            throw new IllegalStateException("O campo isAtivo do mercado está nulo: para realizar " +
                    "a validação do mercado, o campo isAtivo não pode estar nulo");
        }

        registerEvent(new ValidacaoMercadoIsAtivoPorIdEIsAtivoEvent(id, isAtivo));
    }
}
