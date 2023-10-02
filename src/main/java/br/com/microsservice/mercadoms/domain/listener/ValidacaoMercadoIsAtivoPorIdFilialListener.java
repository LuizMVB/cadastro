package br.com.microsservice.mercadoms.domain.listener;

import br.com.microsservice.mercadoms.domain.event.ValidacaoMercadoIsAtivoPorIdFilialEvent;
import br.com.microsservice.mercadoms.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMercadoIsAtivoPorIdFilialListener {

    @Autowired
    private MercadoRepository mercadoRepository;

    @EventListener
    public void validar(ValidacaoMercadoIsAtivoPorIdFilialEvent event) {
        var mercado = mercadoRepository
                .findByFilialId(event.getIdFilial())
                .orElseThrow(EntityNotFoundException::new);

        if(mercado.getIsAtivo().compareTo(Boolean.TRUE) == 0) {
            return;
        }

        throw new RuntimeException("O mercado está inativo: não é possível realizar " +
                "inclusão e atualizacão de filiais que possuem mercados inativos");
    }

}
