package br.com.microsservice.mercadoms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    @NotNull
    @NotEmpty
    private String cep;

    @NotNull
    @NotEmpty
    private String uf;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    private String bairro;

    @NotNull
    @NotEmpty
    private String endereco;

    @NotNull
    @NotEmpty
    private String complemento;

    @NotNull
    @NotEmpty
    private String numero;

}
