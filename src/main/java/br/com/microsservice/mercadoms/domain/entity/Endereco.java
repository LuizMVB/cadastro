package br.com.microsservice.mercadoms.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Endereco {

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(length = 2, nullable = false)
    private String uf;

    @Column(length = 20, nullable = false)
    private String cidade;

    @Column(length = 40, nullable = false)
    private String bairro;

    @Column(length = 40, nullable = false)
    private String endereco;

    @Column(length = 40, nullable = false)
    private String complemento;

    @Column(length = 6, nullable = false)
    private String numero;

}
