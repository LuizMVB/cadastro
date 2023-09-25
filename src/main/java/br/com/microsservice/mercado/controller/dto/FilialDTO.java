package br.com.microsservice.mercado.controller.dto;

import br.com.microsservice.mercado.domain.TipoEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilialDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    private Boolean isAtivo = true;

    @NotNull
    private TipoEnum tipo;

}
