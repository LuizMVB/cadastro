package br.com.microsservice.mercado.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MercadoDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String cnpj;

    private Boolean isAtivo = true;

    @Valid
    @NotNull
    private List<FilialDTO> filialList;

}
