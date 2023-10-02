package br.com.microsservice.mercadoms.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OnChangeIsAtivoMercadoEvent {
    private final Long id;
    private final Boolean isAtivo;
    private final Boolean lastIsAtivo;
}
