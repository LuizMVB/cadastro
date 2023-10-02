package br.com.microsservice.mercadoms.domain.entity;

import br.com.microsservice.mercadoms.domain.event.OnChangeIsAtivoMercadoEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Mercado extends AbstractAggregateRoot<Mercado> {

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

    @Transient
    private Boolean lastIsAtivo;

    public void setIsAtivo(Boolean ativo) {
        lastIsAtivo = isAtivo;
        isAtivo = ativo;
        registerEvent(new OnChangeIsAtivoMercadoEvent(id, isAtivo, lastIsAtivo));
    }

    public void setFilialList(@NotNull List<Filial> filialList) {
        this.filialList = filialList;
        this.filialList.forEach(filial -> filial.setMercado(this));
    }

    public void addFilial(@NotNull Filial filial) {
        filial.setMercado(this);
        filialList.add(filial);
    }

    public List<Filial> getFiliaisAtivas() {
        return filialList.stream()
                .filter(Filial::getIsAtivo)
                .toList();
    }

}
