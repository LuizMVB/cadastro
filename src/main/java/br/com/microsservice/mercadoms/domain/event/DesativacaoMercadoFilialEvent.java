package br.com.microsservice.mercadoms.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DesativacaoMercadoFilialEvent {

    private final Long idFilial;

}
