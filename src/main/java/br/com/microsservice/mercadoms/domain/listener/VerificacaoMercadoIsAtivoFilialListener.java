package br.com.microsservice.mercadoms.domain.listener;

import br.com.microsservice.mercadoms.domain.event.VerificacaoMercadoIsAtivoFilialEvent;
import br.com.microsservice.mercadoms.service.ControleAtivacaoMercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Order(1)
public class VerificacaoMercadoIsAtivoFilialListener {

    @Autowired
    private ControleAtivacaoMercadoService controleAtivacaoMercadoService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void verificarSeMercadoEstaAtivo(VerificacaoMercadoIsAtivoFilialEvent event) {
        Boolean mercadoIsAtivo = controleAtivacaoMercadoService
                .isMercadoAtivo(event.getIdFilial());

        if(!mercadoIsAtivo) {
            throw new RuntimeException("O mercado está inativo: não é possível " +
                    "criar ou atualizar uma filial de um mercado desativado");
        }
    }

}
