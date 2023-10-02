package br.com.microsservice.mercadoms.domain.listener;

import br.com.microsservice.mercadoms.domain.entity.Filial;
import br.com.microsservice.mercadoms.domain.entity.Mercado;
import br.com.microsservice.mercadoms.domain.event.AtualizacaoFilialListPorIdMercadoEIsAtivoEvent;
import br.com.microsservice.mercadoms.repository.FilialRepository;
import br.com.microsservice.mercadoms.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class AtualizacaoFilialListPorIdMercadoEIsAtivoListener {

    @Autowired
    private FilialRepository filialRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void atualizarFilialListPorIdMercadoEIsAtivo(AtualizacaoFilialListPorIdMercadoEIsAtivoEvent event) {
        Long id = event.getMercado().getId();
        Boolean isAtivo = event.getMercado().getIsAtivo();

        List<Filial> filialList = filialRepository.findByIdMercadoAndIsAtivoTrue(id);

        if(isAtivo || CollectionUtils.isEmpty(filialList)) {
            return;
        }

        filialList.forEach(Filial::desativar);

        filialRepository.saveAll(filialList);
    }

}
