package br.com.microsservice.mercadoms.domain.event;

import br.com.microsservice.mercadoms.domain.entity.Mercado;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AtualizacaoFilialListPorIdMercadoEIsAtivoEvent {

    private final Mercado mercado;

}
