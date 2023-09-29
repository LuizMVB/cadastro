package br.com.microsservice.mercadoms.dto;

import br.com.microsservice.mercadoms.domain.TipoFilialEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilialDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    private Boolean isAtivo = true;

    @NotNull
    private TipoFilialEnum tipo;

    @Valid
    @NotNull
    private EnderecoDTO endereco;

}
