package br.com.microsservice.mercadoms.domain.listener;

import br.com.microsservice.mercadoms.domain.event.OnChangeIsAtivoFilialEvent;
import br.com.microsservice.mercadoms.service.ControleAtivacaoMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OnChangeIsAtivoFilialListener {

    @Autowired
    private ControleAtivacaoMercadoService controleAtivacaoMercadoService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onChangeIsAtivoFilial(OnChangeIsAtivoFilialEvent event) {
        if(event.getLastIsAtivo().equals(event.getIsAtivo()) || event.getIsAtivo()) {
            return;
        }

        controleAtivacaoMercadoService.desativar(event.getId());
    }

}
