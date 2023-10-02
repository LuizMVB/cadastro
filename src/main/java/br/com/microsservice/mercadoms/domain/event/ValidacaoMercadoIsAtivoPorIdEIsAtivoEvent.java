package br.com.microsservice.mercadoms.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ValidacaoMercadoIsAtivoPorIdEIsAtivoEvent {

    private final Long id;
    private final Boolean isAtivo;

}
