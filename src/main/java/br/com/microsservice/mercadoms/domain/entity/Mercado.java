package br.com.microsservice.mercadoms.domain.entity;

import br.com.microsservice.mercadoms.domain.Entidade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Mercado extends Entidade<Mercado> {

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

    @PrePersist
    @PreUpdate
    public void onPrePersistOrPreUpdate() {
        if(isAtivo) {
            return;
        }

        if(CollectionUtils.isEmpty(filialList)) {
            return;
        }

        filialList.forEach(Filial::desativar);
    }

    public void setFilialList(List<Filial> filialList) {
        this.filialList = filialList;

        if(CollectionUtils.isEmpty(filialList)) {
            return;
        }

        filialList.forEach(filial -> filial.setMercado(this));
    }

    public void addFilial(Filial filial) {
        if(CollectionUtils.isEmpty(filialList)) {
            filialList = new ArrayList<>();
        }
        filial.setMercado(this);
        filialList.add(filial);
    }

}
