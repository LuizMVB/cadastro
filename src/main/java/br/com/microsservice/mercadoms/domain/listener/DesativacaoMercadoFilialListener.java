package br.com.microsservice.mercadoms.domain.listener;

import br.com.microsservice.mercadoms.domain.event.DesativacaoMercadoFilialEvent;
import br.com.microsservice.mercadoms.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

@Component
public class DesativacaoMercadoFilialListener {

    @Autowired
    private MercadoRepository mercadoRepository;

    @TransactionalEventListener(phase = BEFORE_COMMIT)
    public void desativarMercadoFilial(DesativacaoMercadoFilialEvent event) {
        var mercado = mercadoRepository
                .findByFilialId(event.getIdFilial())
                .orElseThrow(EntityNotFoundException::new);
        mercado.setIsAtivo(false);
        mercadoRepository.save(mercado);
    }

}
