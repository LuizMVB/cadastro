package br.com.microsservice.mercadoms.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ValidacaoMercadoIsAtivoPorIdFilialEvent {

    private final Long idFilial;

}
