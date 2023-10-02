package br.com.microsservice.mercadoms.domain.listener;

import br.com.microsservice.mercadoms.domain.event.OnChangeIsAtivoMercadoEvent;
import br.com.microsservice.mercadoms.service.ControleAtivacaoFilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OnChangeIsAtivoMercadoListener {

    @Autowired
    private ControleAtivacaoFilialService controleAtivacaoFilialService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onChangeIsAtivoFilial(OnChangeIsAtivoMercadoEvent event) {
        if(event.getLastIsAtivo().equals(event.getIsAtivo())) {
            return;
        }

        if(event.getIsAtivo()) {
            return;
        }

        controleAtivacaoFilialService.desativar(event.getId());
    }

}
